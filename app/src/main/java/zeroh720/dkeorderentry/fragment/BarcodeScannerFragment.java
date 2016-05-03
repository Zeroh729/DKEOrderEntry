package zeroh720.dkeorderentry.fragment;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.samples.vision.barcodereader.ui.camera.BarcodeGraphic;
import com.google.android.gms.samples.vision.barcodereader.ui.camera.CameraSource;
import com.google.android.gms.samples.vision.barcodereader.ui.camera.CameraSourcePreview;
import com.google.android.gms.samples.vision.barcodereader.ui.camera.GraphicOverlay;
import com.google.android.gms.samples.vision.barcodereader.ui.camera.BarcodeTrackerFactory;
import com.google.android.gms.vision.MultiProcessor;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;

import zeroh720.dkeorderentry.R;
import zeroh720.dkeorderentry.application.DKEOrderEntry;
import zeroh720.dkeorderentry.interfaces.OnScanListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class BarcodeScannerFragment extends BaseFragment {
    private CameraSource cameraSource;
    private CameraSourcePreview cameraSourcePreview;
    private GraphicOverlay<BarcodeGraphic> graphicOverlay;
    private OnScanListener listener;

    private GestureDetector gestureDetector;

    public BarcodeScannerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_barcode_scanner, container, false);

        v.setOnTouchListener(onScreenTouch);


        cameraSourcePreview = (CameraSourcePreview) v.findViewById(R.id.preview);
        graphicOverlay = (GraphicOverlay<BarcodeGraphic>) v.findViewById(R.id.graphicOverlay);

        gestureDetector = new GestureDetector(getContext(), new CaptureGestureListener());

        createCameraSource();

        return v;
    }

    public void setOnScanListener(OnScanListener listener) {
        this.listener = listener;
    }

    private class CaptureGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            return onTap(e.getRawX(), e.getRawY()) || super.onSingleTapConfirmed(e);
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        startCameraSource();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (cameraSourcePreview != null) {
            cameraSourcePreview.stop();
        }
    }

    private View.OnTouchListener onScreenTouch = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return gestureDetector.onTouchEvent(event);
        }
    };

    @SuppressLint("InlinedApi")
    private void createCameraSource() {
        Context context = DKEOrderEntry.context;

        BarcodeDetector barcodeDetector = new BarcodeDetector.Builder(context).build();
        BarcodeTrackerFactory barcodeFactory = new BarcodeTrackerFactory(graphicOverlay, new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(Message msg) {
//                ProgressDialog progressDialog = new ProgressDialog(getActivity());
//                progressDialog.setMessage("Loading");
//
//                progressDialog.show();

//                Firebase firebaseRef = new Firebase(DbConstants.FirebaseURL);
//
//                firebaseRef.child(DbConstants.Col_Product).addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        HashMap<String, Object> data = (HashMap)dataSnapshot.getValue();
//                        Iterator iterator = data.entrySet().iterator();
//                        while(iterator.hasNext()){
//                            HashMap<String, Object> obj = (HashMap)iterator.next();
//
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(FirebaseError firebaseError) {
//
//                    }
//                });

                Barcode barcode = (Barcode)msg.obj;
                if(listener != null){
                    listener.onScan(barcode);
                }
            }
        });

        barcodeDetector.setProcessor(
                new MultiProcessor.Builder<>(barcodeFactory).build());

        if (!barcodeDetector.isOperational()) {
            Log.w("TEST", "Detector dependencies are not yet available.");

            IntentFilter lowstorageFilter = new IntentFilter(Intent.ACTION_DEVICE_STORAGE_LOW);
            boolean hasLowStorage = getActivity().registerReceiver(null, lowstorageFilter) != null;

            if (hasLowStorage) {
                Toast.makeText(getContext(), "Face detector dependencies cannot be downloaded due to low device storage", Toast.LENGTH_LONG).show();
            }
        }

        CameraSource.Builder builder = new CameraSource.Builder(DKEOrderEntry.context, barcodeDetector)
                .setFacing(CameraSource.CAMERA_FACING_BACK)
                .setRequestedPreviewSize(1600, 1024)
                .setRequestedFps(15.0f);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            builder = builder.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
        }

        cameraSource = builder.build();
    }

    private void startCameraSource() throws SecurityException {
        int code = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(
                DKEOrderEntry.context);
        if (code != ConnectionResult.SUCCESS) {
            Dialog dlg =
                    GoogleApiAvailability.getInstance().getErrorDialog(getActivity(), code, 9001);
            dlg.show();
        }

        if (cameraSource != null) {
            try {
                cameraSourcePreview.start(cameraSource, graphicOverlay);
            } catch (IOException e) {
                Log.e("TEST", "Unable to start camera source.", e);
                cameraSource.release();
                cameraSource = null;
            }
        }
    }


    private boolean onTap(float rawX, float rawY) {
        BarcodeGraphic graphic = graphicOverlay.getFirstGraphic();
        Barcode barcode = null;
        if (graphic != null) {
            barcode = graphic.getBarcode();
            if (barcode != null) {
                Intent data = new Intent();
                data.putExtra("barcode", barcode);
                //TODO: startActivity(getActivityContext(), OrderPopupFragment.class);
                Toast.makeText(getContext(), "Barcode: " + barcode.rawValue, Toast.LENGTH_SHORT).show();
            }
            else {
                Log.d("TEST", "barcode data is null");
            }
        }
        else {
            Log.d("TEST","no barcode detected");
        }
        return barcode != null;
    }

}
