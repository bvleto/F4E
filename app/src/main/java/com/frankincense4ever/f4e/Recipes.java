package com.frankincense4ever.f4e;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.frankincense4ever.f4e.util.IabHelper;
import com.frankincense4ever.f4e.util.IabResult;
import com.frankincense4ever.f4e.util.Inventory;
import com.frankincense4ever.f4e.util.Purchase;

public class Recipes extends AppCompatActivity {

    private Button walk_in_the_woods;
    private Button view_recipe_walk_in_the_woods;

    private static final String TAG = "frankincense4ever";
    IabHelper mHelper;
    static final String ITEM_SKU = "a_walk_in_the_woods";


    Button bumps_bruises;

    public void bumps_bruises(View view) {
        Intent intent = new Intent(this, bumps_bruises.class);
        startActivity(intent);
    }

    Button fall_in_love;

    public void fall_in_love(View view) {
        Intent intent = new Intent(this, fall_in_love.class);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        walk_in_the_woods = (Button) findViewById(R.id.walk_in_the_woods);
        view_recipe_walk_in_the_woods = (Button)findViewById(R.id.view_recipe_walk_in_the_woods);
        view_recipe_walk_in_the_woods.setEnabled(false);
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


    public void view_recipe_walk_in_the_woods(View view)
    {
        view_recipe_walk_in_the_woods.setEnabled(false);
        walk_in_the_woods.setEnabled(true);
    }

    public void walk_in_the_woods(View view) {
        mHelper.launchPurchaseFlow(this, ITEM_SKU, 10001,
                mPurchaseFinishedListener, "view_recipe_walk_in_the_woods");
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
                walk_in_the_woods.setEnabled(false);
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
                            view_recipe_walk_in_the_woods.setEnabled(true);





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



