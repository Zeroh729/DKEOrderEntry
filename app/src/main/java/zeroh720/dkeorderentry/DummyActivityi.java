package zeroh720.dkeorderentry;

import android.app.Activity;
import android.os.Bundle;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.HashMap;

import zeroh720.dkeorderentry.value.DbConstants;

public class DummyActivityi extends Activity {
    Firebase firebaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dummy_activityi);

        firebaseRef = new Firebase(DbConstants.FirebaseURL);

        HashMap map;

        map = new HashMap();
        map.put("branch", "Easy Day Shop Makati");
        map.put("merchId", "583982e5-0b8b-422c-9c57-708dab17e6b3");
        map.put("name", "Shiela Garcia");
        map.put("time", "1461685620000");
        firebaseRef.child(DbConstants.Col_TransHistory).push().setValue(map, new Firebase.CompletionListener() {
            @Override
            public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                HashMap map2;

                map2 = new HashMap();
                map2.put("code", "4800195033793");
                map2.put("name", "LEGO OVAL VACUUM LUNCH BOX 900ML");
                map2.put("qty", "10");
                firebase.child("items").push().setValue(map2);

                map2 = new HashMap();
                map2.put("code", "4800195033823");
                map2.put("name", "D.W. BAMBOO WOK BRUSH");
                map2.put("qty", "20");
                firebase.child("items").push().setValue(map2);

                map2 = new HashMap();
                map2.put("code", "4800195036145");
                map2.put("name", "BIG ORANGE WATER BOTTLE 550ML #2802");
                map2.put("qty", "30");
                firebase.child("items").push().setValue(map2);

                map2 = new HashMap();
                map2.put("code", "4800195036251");
                map2.put("name", "D.W. NYLON ROUND FRUIT BASKET - XL");
                map2.put("qty", "15");
                firebase.child("items").push().setValue(map2);
            }
        });


        //2
        map = new HashMap();
        map.put("branch", "Ever Supermarket Makati");
        map.put("merchId", "2594c038-98ac-45ef-a99d-37b4ed359fcc");
        map.put("name", "Alvin O. Diyan");
        map.put("time", "1461696960000");
        firebaseRef.child(DbConstants.Col_TransHistory).push().setValue(map, new Firebase.CompletionListener() {
            @Override
            public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                HashMap map2;

                map2 = new HashMap();
                map2.put("code", "4800195033793");
                map2.put("name", "LEGO OVAL VACUUM LUNCH BOX 900ML");
                map2.put("qty", "11");
                firebase.child("items").push().setValue(map2);

                map2 = new HashMap();
                map2.put("code", "4800195033830");
                map2.put("name", "D.W. ROYAL S/S HEAVY DUTY RD. POT HOLDER-16CM");
                map2.put("qty", "25");
                firebase.child("items").push().setValue(map2);

                map2 = new HashMap();
                map2.put("code", "4800195071269");
                map2.put("name", "D.W. ROYAL S/S FAST ELECT. CUP 16CM");
                map2.put("qty", "10");
                firebase.child("items").push().setValue(map2);

                map2 = new HashMap();
                map2.put("code", "4800195036251");
                map2.put("name", "D.W. NYLON ROUND FRUIT BASKET - XL");
                map2.put("qty", "13");
                firebase.child("items").push().setValue(map2);
            }
        });



        //3
        map = new HashMap();
        map.put("branch", "South Supermarket Makati");
        map.put("merchId", "c61f6b0c-f7bd-4d83-bbec-e3f65b7fb0a5");
        map.put("name", "Ross Sedillan");
        map.put("time", "1461528060000");
        firebaseRef.child(DbConstants.Col_TransHistory).push().setValue(map, new Firebase.CompletionListener() {
            @Override
            public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                HashMap map2;

                map2 = new HashMap();
                map2.put("code", "4800195033793");
                map2.put("name", "LEGO OVAL VACUUM LUNCH BOX 900ML");
                map2.put("qty", "15");
                firebase.child("items").push().setValue(map2);

                map2 = new HashMap();
                map2.put("code", "4800195033830");
                map2.put("name", "D.W. ROYAL S/S HEAVY DUTY RD. POT HOLDER-16CM");
                map2.put("qty", "28");
                firebase.child("items").push().setValue(map2);
            }
        });



        //4
        map = new HashMap();
        map.put("branch", "Super 8 Grocery");
        map.put("merchId", "cca8f293-f2fc-4a3c-b93e-bac1513e680e");
        map.put("name", "Mirie An Cepeno");
        map.put("time", "1461501420000");
        firebaseRef.child(DbConstants.Col_TransHistory).push().setValue(map, new Firebase.CompletionListener() {
            @Override
            public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                HashMap map2;

                map2 = new HashMap();
                map2.put("code", "4800195071771");
                map2.put("name", "D.W. PEARL STAINLESS COVER KNOBS");
                map2.put("qty", "15");
                firebase.child("items").push().setValue(map2);

                map2 = new HashMap();
                map2.put("code", "4800195034974");
                map2.put("name", "PORCELAIN PIGGY SAVING BANK 5\"");
                map2.put("qty", "28");
                firebase.child("items").push().setValue(map2);

                map2 = new HashMap();
                map2.put("code", "4800195070989");
                map2.put("name", "D.W. ROYAL S/S ELEC. HEATING CUP 12CM");
                map2.put("qty", "42");
                firebase.child("items").push().setValue(map2);
            }
        });



        //5
        map = new HashMap();
        map.put("branch", "South Supermarket Makati");
        map.put("merchId", "c61f6b0c-f7bd-4d83-bbec-e3f65b7fb0a5");
        map.put("name", "Ross Sedilla");
        map.put("time", "1461698280000");
        firebaseRef.child(DbConstants.Col_TransHistory).push().setValue(map, new Firebase.CompletionListener() {
            @Override
            public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                HashMap map2;

                map2 = new HashMap();
                map2.put("code", "4800195033793");
                map2.put("name", "LEGO OVAL VACUUM LUNCH BOX 900ML");
                map2.put("qty", "18");
                firebase.child("items").push().setValue(map2);

                map2 = new HashMap();
                map2.put("code", "4800195034974");
                map2.put("name", "PORCELAIN PIGGY SAVING BANK 5\"");
                map2.put("qty", "38");
                firebase.child("items").push().setValue(map2);

                map2 = new HashMap();
                map2.put("code", "4800195070989");
                map2.put("name", "D.W. ROYAL S/S ELEC. HEATING CUP 12CM");
                map2.put("qty", "52");
                firebase.child("items").push().setValue(map2);
            }
        });



        //6
        map = new HashMap();
        map.put("branch", "Puregold Price Club Makati");
        map.put("merchId", "603318e4-9442-4353-afab-9cfd55007f73");
        map.put("name", "Sam Brian Maglipon");
        map.put("time", "1461698280000");
        firebaseRef.child(DbConstants.Col_TransHistory).push().setValue(map, new Firebase.CompletionListener() {
            @Override
            public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                HashMap map2;

                map2 = new HashMap();
                map2.put("code", "4800195035064");
                map2.put("name", "BOTTLE SCOURING SPONGE W/HDLE");
                map2.put("qty", "20");
                firebase.child("items").push().setValue(map2);

                map2 = new HashMap();
                map2.put("code", "4800195034974");
                map2.put("name", "PORCELAIN PIGGY SAVING BANK 5\"");
                map2.put("qty", "38");
                firebase.child("items").push().setValue(map2);

                map2 = new HashMap();
                map2.put("code", "4800195070989");
                map2.put("name", "D.W. ROYAL S/S ELEC. HEATING CUP 12CM");
                map2.put("qty", "52");
                firebase.child("items").push().setValue(map2);
            }
        });


/*
        map = new HashMap();
        map.put("code", "4800195034882");
        map.put("name", "MINISO DIY POPSUCLE MOULDER 6'S #001");
        map.put("qty", "1000");
        firebaseRef.child(DbConstants.Col_Product).push().setValue(map);

        map = new HashMap();
        map.put("code", "4800195034875");
        map.put("name", "MINISO DIY POPSUCLE MOULDER 8'S #002");
        map.put("qty", "900");
        firebaseRef.child(DbConstants.Col_Product).push().setValue(map);

        map = new HashMap();
        map.put("code", "4800195034912");
        map.put("name", "BIG ORANGE SPOON & FORK CONTAINER");
        map.put("qty", "2200");
        firebaseRef.child(DbConstants.Col_Product).push().setValue(map);

        map = new HashMap();
        map.put("code", "4800195034929");
        map.put("name", "BIG ORANGE S/S SPOON & FORK W/CASE");
        map.put("qty", "80");
        firebaseRef.child(DbConstants.Col_Product).push().setValue(map);

        map = new HashMap();
        map.put("code", "4800195071771");
        map.put("name", "D.W. PEARL STAINLESS COVER KNOBS");
        map.put("qty", "300");
        firebaseRef.child(DbConstants.Col_Product).push().setValue(map);

        map = new HashMap();
        map.put("code", "4800195034974");
        map.put("name", "PORCELAIN PIGGY SAVING BANK 5\"");
        map.put("qty", "7000");
        firebaseRef.child(DbConstants.Col_Product).push().setValue(map);

        map = new HashMap();
        map.put("code", "4800195034981");
        map.put("name", "D.W. PRINTED PORC. TEA POT 5\"");
        map.put("qty", "400");
        firebaseRef.child(DbConstants.Col_Product).push().setValue(map);

        map = new HashMap();
        map.put("code", "4800195034998");
        map.put("name", "D.W. WHITE PORC. COFFEE MUG 400ML");
        map.put("qty", "600");
        firebaseRef.child(DbConstants.Col_Product).push().setValue(map);

        map = new HashMap();
        map.put("code", "4800195035056");
        map.put("name", "ALL PURPOSE SCOURING SPONGE 3'S");
        map.put("qty", "190");
        firebaseRef.child(DbConstants.Col_Product).push().setValue(map);

        map = new HashMap();
        map.put("code", "4800195035064");
        map.put("name", "BOTTLE SCOURING SPONGE W/HDLE");
        map.put("qty", "2080");
        firebaseRef.child(DbConstants.Col_Product).push().setValue(map);

        map = new HashMap();
        map.put("code", "4800195070989");
        map.put("name", "D.W. ROYAL S/S ELEC. HEATING CUP 12CM");
        map.put("qty", "370");
        firebaseRef.child(DbConstants.Col_Product).push().setValue(map);

        map = new HashMap();
        map.put("code", "4800195070996");
        map.put("name", "D.W. ROYAL S/S ELEC. HEATING CUP 14CM");
        map.put("qty", "4600");
        firebaseRef.child(DbConstants.Col_Product).push().setValue(map);

        map = new HashMap();
        map.put("code", "4800195071269");
        map.put("name", "D.W. ROYAL S/S FAST ELECT. CUP 16CM");
        map.put("qty", "5500");
        firebaseRef.child(DbConstants.Col_Product).push().setValue(map);

        map = new HashMap();
        map.put("code", "4800195071900");
        map.put("name", "D.W. PEARL BAKELITE COVER KNOB");
        map.put("qty", "1800");
        firebaseRef.child(DbConstants.Col_Product).push().setValue(map);

        map = new HashMap();
        map.put("code", "4800195036398");
        map.put("name", "BIG ORANGE PLASTIC LAUNDRY CLIP 12'S - AB");
        map.put("qty", "270");
        firebaseRef.child(DbConstants.Col_Product).push().setValue(map);

        map = new HashMap();
        map.put("code", "4800195036404");
        map.put("name", "BIG ORANGE PLASTIC LAUNDRY CLIP 24'S - AB");
        map.put("qty", "380");
        firebaseRef.child(DbConstants.Col_Product).push().setValue(map);

        map = new HashMap();
        map.put("code", "4800195036213");
        map.put("name", "D.W. NYLON ROUND FRUIT BASKET - XS");
        map.put("qty", "470");
        firebaseRef.child(DbConstants.Col_Product).push().setValue(map);

        map = new HashMap();
        map.put("code", "4800195036251");
        map.put("name", "D.W. NYLON ROUND FRUIT BASKET - XL");
        map.put("qty", "550");
        firebaseRef.child(DbConstants.Col_Product).push().setValue(map);

        map = new HashMap();
        map.put("code", "4800195036268");
        map.put("name", "D.W. NYLON OVAL FRUIT TRAY 22X16X5CM");
        map.put("qty", "250");
        firebaseRef.child(DbConstants.Col_Product).push().setValue(map);

        map = new HashMap();
        map.put("code", "4800195036275");
        map.put("name", "D.W. NYLON OVAL FRUIT TRAY 24X16X6CM");
        map.put("qty", "1400");
        firebaseRef.child(DbConstants.Col_Product).push().setValue(map);

        map = new HashMap();
        map.put("code", "4800195036305");
        map.put("name", "D.W. NYLON OVAL BREAD BASKET 23X18X8CM");
        map.put("qty", "2500");
        firebaseRef.child(DbConstants.Col_Product).push().setValue(map);

        map = new HashMap();
        map.put("code", "4800195036114");
        map.put("name", "CLIBE WATER BOTTLE W/RUBBER DSGN 350ML #1121");
        map.put("qty", "1200");
        firebaseRef.child(DbConstants.Col_Product).push().setValue(map);

        map = new HashMap();
        map.put("code", "4800195036121");
        map.put("name", "CLIBE WATER BOTTLE W/RUBBER DSGN 450ML #1122");
        map.put("qty", "780");
        firebaseRef.child(DbConstants.Col_Product).push().setValue(map);

        map = new HashMap();
        map.put("code", "4800195036138");
        map.put("name", "CLIBE WATER BOTTLE W/RUBBER DSGN 600ML #1123");
        map.put("qty", "960");
        firebaseRef.child(DbConstants.Col_Product).push().setValue(map);

        map = new HashMap();
        map.put("code", "4800195036145");
        map.put("name", "BIG ORANGE WATER BOTTLE 550ML #2802");
        map.put("qty", "750");
        firebaseRef.child(DbConstants.Col_Product).push().setValue(map);

        map = new HashMap();
        map.put("code", "4800195036152");
        map.put("name", "BIG ORANGE WATER BOTTLE 650ML #2801");
        map.put("qty", "1000");
        firebaseRef.child(DbConstants.Col_Product).push().setValue(map);

        map = new HashMap();
        map.put("code", "4800195033823");
        map.put("name", "D.W. BAMBOO WOK BRUSH");
        map.put("qty", "700");
        firebaseRef.child(DbConstants.Col_Product).push().setValue(map);

        map = new HashMap();
        map.put("code", "4800195033830");
        map.put("name", "D.W. ROYAL S/S HEAVY DUTY RD. POT HOLDER-16CM");
        map.put("qty", "2100");
        firebaseRef.child(DbConstants.Col_Product).push().setValue(map);

        map = new HashMap();
        map.put("code", "4800195033892");
        map.put("name", "D.W. ROYAL S/S HEAVY DUTY RD. POT HOLDER-20CM");
        map.put("qty", "900");
        firebaseRef.child(DbConstants.Col_Product).push().setValue(map);

        map = new HashMap();
        map.put("code", "4800195033793");
        map.put("name", "LEGO OVAL VACUUM LUNCH BOX 900ML");
        map.put("qty", "100");
        firebaseRef.child(DbConstants.Col_Product).push().setValue(map);*/
    }
}
