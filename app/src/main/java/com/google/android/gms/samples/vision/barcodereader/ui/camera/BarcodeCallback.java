package com.google.android.gms.samples.vision.barcodereader.ui.camera;

import com.google.android.gms.vision.barcode.Barcode;

public interface BarcodeCallback {
    void onFound(Barcode barcode);
}
