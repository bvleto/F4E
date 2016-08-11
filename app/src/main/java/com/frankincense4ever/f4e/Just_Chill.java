package com.frankincense4ever.f4e;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.frankincense4ever.f4e.util.IabHelper;
import com.frankincense4ever.f4e.util.IabResult;
import com.frankincense4ever.f4e.util.Inventory;
import com.frankincense4ever.f4e.util.Purchase;

public class Just_Chill extends AppCompatActivity {

    private Button Just_Chill;
    public void Just_Chill(View view) {
/*
        Intent intent = new Intent(this, view_recipe_just_chill.class);
        startActivity(intent);
*/
        mHelper.launchPurchaseFlow(this, ITEM_SKU, 10001,
                mPurchaseFinishedListener, "Just Chill Blend");

    }

    private Button view_recipe_just_chill;
    public void view_recipe_just_chill(View view)  {
//        view_recipe_just_chill.setEnabled(true); // TODO: set back to false; true for testing purposes
        //       Just_Chill.setEnabled(true);
        //todo: display view recipe just chill
        //display content_view_recipe_just_chill.xml

        //TODO: this will display the relevant view
        //Intent intent = new Intent(this, view_recipe_just_chill.class);
        //startActivity(intent); // TODO: this method seems to be the last line that displays the view ...

        Intent intent = new Intent(this, view_recipe_just_chill.class);
        startActivity(intent);
    }


    private static final String TAG = "Just Chill Blend";
    IabHelper mHelper;
    static final String ITEM_SKU = "just_chill";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_just__chill);

        Just_Chill = (Button) findViewById(R.id.Just_Chill);
        view_recipe_just_chill = (Button)findViewById(R.id.view_recipe_just_chill2);
        view_recipe_just_chill.setEnabled(false);
        String base64EncodedPublicKey =
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA0kxWsr5aHiDPeLrmxY6My/2wuF2Fw6znGp3KlpTHniTQHQ0K/+1wlLUTq9Z8i/OtLTuqFgHN0IVMRfUsqTxvCY0rByyvlGiq2onxjcErK6Qbhi+Bkcg1+zTPq8S9kSgeuPShfIHFWNza8Q+n+0xzauQ3Lp99gAyl63VU9iU8Akxh8vitwmotuSQqUSz3iE0tpvr5Xo27E3JHiXxuMuHjP1mgS8cNeT0zYeeVgdwJPdmtnd5Is45dMEF5ogz/PlEVPHdP1Z4Kn+EQrFplqAj/YqX55Fg0UY3MKRYS6vclvCX0ADCTMkbcOFKLxOUC7+yvVFPCVA+9YZh8ZrSkJPfOhQIDAQAB";

        mHelper = new IabHelper(this, base64EncodedPublicKey);

        mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
            public void onIabSetupFinished(IabResult result) {
                if (!result.isSuccess()) {
                    Log.d(TAG, "In-app Billing setup failed: " + result);
                }else {
                    Log.d(TAG, "In-app Billing is set up OK");
                }
            }
        });


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        if (!mHelper.handleActivityResult(requestCode,
                resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);

        }
    }

    IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener
            = new IabHelper.OnIabPurchaseFinishedListener() {
        public void onIabPurchaseFinished(IabResult result, Purchase purchase) {
            if (result.isFailure()) {
                Log.d(TAG, "Error purchasing: " + result);
                return;
            } else if (purchase.getSku().equals(ITEM_SKU)) {
                consumeItem();
                Just_Chill.setEnabled(false);
            }
        }
    };

    public void consumeItem() {
        mHelper.queryInventoryAsync(mReceivedInventoryListener);
    }

    IabHelper.QueryInventoryFinishedListener mReceivedInventoryListener
            = new IabHelper.QueryInventoryFinishedListener() {
        public void onQueryInventoryFinished(IabResult result,
                                             Inventory inventory) {

            if (result.isFailure()) {
                // handle error here
            } else {
                mHelper.consumeAsync(inventory.getPurchase(ITEM_SKU),
                        mConsumeFinishedListener);
            }
        };

        IabHelper.OnConsumeFinishedListener mConsumeFinishedListener =
                new IabHelper.OnConsumeFinishedListener() {
                    public void onConsumeFinished(Purchase purchase, IabResult result) {
                        if (result.isSuccess()) {
                            view_recipe_just_chill.setEnabled(true);






                            // provision the in-app purchase to the user
                            // (for example, credit 50 gold coins to player's character)


                        } else {
                            // handle error

                        }
                    }

                };
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mHelper != null) mHelper.dispose();
        mHelper = null;
    }

}



