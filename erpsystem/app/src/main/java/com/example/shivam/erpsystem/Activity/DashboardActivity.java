package com.example.shivam.erpsystem.Activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.shivam.erpsystem.Fragment.AccountFragment;
import com.example.shivam.erpsystem.Fragment.CustomerFragment;
import com.example.shivam.erpsystem.Fragment.DashboradFragment;
import com.example.shivam.erpsystem.Adapter.ExpandableListAdapter;
import com.example.shivam.erpsystem.Fragment.CategoryFragment;
import com.example.shivam.erpsystem.Fragment.ExpenseVoucherFragment;
import com.example.shivam.erpsystem.Fragment.FollowUpFragment;
import com.example.shivam.erpsystem.Fragment.ManageLeadsFragment;
import com.example.shivam.erpsystem.Fragment.ProductFragment;
import com.example.shivam.erpsystem.Fragment.PurchaseOrderFragment;
import com.example.shivam.erpsystem.Fragment.PurchaseReportFragment;
import com.example.shivam.erpsystem.Fragment.PurchaseVoucherFragment;
import com.example.shivam.erpsystem.Fragment.QuotationFragment;
import com.example.shivam.erpsystem.Fragment.QuotationReportFragment;
import com.example.shivam.erpsystem.Fragment.ReceiptsFragment;
import com.example.shivam.erpsystem.Fragment.ReportDailyTransactionFragment;
import com.example.shivam.erpsystem.Fragment.SalesBillFragment;
import com.example.shivam.erpsystem.Fragment.SalesReportFragment;
import com.example.shivam.erpsystem.Fragment.StockReportFragment;
import com.example.shivam.erpsystem.Fragment.SupplierFragment;
import com.example.shivam.erpsystem.Fragment.TaxDetailsFragment;
import com.example.shivam.erpsystem.Fragment.TaxReportFragment;
import com.example.shivam.erpsystem.Fragment.UnitDetailsFragment;
import com.example.shivam.erpsystem.Model.DashboardActivityModel;
import com.example.shivam.erpsystem.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DashboardActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ExpandableListAdapter expandableListAdapter;
    ExpandableListView expandableListView;
    List<DashboardActivityModel> headerList = new ArrayList<>();
    HashMap<DashboardActivityModel, List<DashboardActivityModel>> childList = new HashMap<>();
    TextView profilename=null,profilemail;
    String mail=null,username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

/*
        Bundle bundle = getIntent().getExtras();
         username=bundle.getString("user_name");
         mail=bundle.getString("user_email");
*/



        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragment, new DashboradFragment());
        fragmentTransaction.commit();

        expandableListView = findViewById(R.id.expandableListView);
        prepareMenuData();
        populateExpandableList();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        /* profilemail = (TextView) navigationView.getHeaderView(0).findViewById(R.id.user_email);
        profilemail.setText(mail);

        profilename=(TextView)navigationView.getHeaderView(0).findViewById(R.id.user_name);
        profilename.setText(username);
*/





    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void prepareMenuData() {

        DashboardActivityModel menuModel = new DashboardActivityModel("Dashboard", true, false); //Menu of Android Tutorial. No sub menus
        headerList.add(menuModel);

        if (!menuModel.hasChildren) {
            childList.put(menuModel, null);
        }

        menuModel = new DashboardActivityModel("Master", true, true); //Menu of Java Tutorials
        headerList.add(menuModel);
        List<DashboardActivityModel> childModelsList = new ArrayList<>();
        DashboardActivityModel childModel = new DashboardActivityModel("Category", false, false);
        childModelsList.add(childModel);

        childModel = new DashboardActivityModel("Product", false, false);
        childModelsList.add(childModel);

        childModel = new DashboardActivityModel("Customer", false, false);
        childModelsList.add(childModel);

        childModel = new DashboardActivityModel("Supplier", false, false);
        childModelsList.add(childModel);

        childModel = new DashboardActivityModel("Account", false, false);
        childModelsList.add(childModel);

        childModel = new DashboardActivityModel("Tax Details", false, false);
        childModelsList.add(childModel);

        childModel = new DashboardActivityModel("Unit Details", false, false);
        childModelsList.add(childModel);


        if (menuModel.hasChildren) {
            Log.d("API123","here");
            childList.put(menuModel, childModelsList);
        }

         menuModel = new DashboardActivityModel("Purchase Order", true, false); //Menu of Android Tutorial. No sub menus
        headerList.add(menuModel);

        if (!menuModel.hasChildren) {
            childList.put(menuModel, null);
        }

         menuModel = new DashboardActivityModel("Sales Bill", true, false); //Menu of Android Tutorial. No sub menus
        headerList.add(menuModel);

        if (!menuModel.hasChildren) {
            childList.put(menuModel, null);
        }


         menuModel = new DashboardActivityModel("Quotation", true, false); //Menu of Android Tutorial. No sub menus
        headerList.add(menuModel);

        if (!menuModel.hasChildren) {
            childList.put(menuModel, null);
        }



        childModelsList = new ArrayList<>();
        menuModel = new DashboardActivityModel("Payment", true, true); //Menu of Python Tutorials
        headerList.add(menuModel);
        childModel = new DashboardActivityModel("Receipts", false, false);
        childModelsList.add(childModel);

        childModel = new DashboardActivityModel("Purchase Voucher", false, false);
        childModelsList.add(childModel);

        childModel = new DashboardActivityModel("Expense Voucher", false, false);

        childModelsList.add(childModel);
        if (menuModel.hasChildren) {
            childList.put(menuModel, childModelsList);
        }

        childModelsList = new ArrayList<>();
        menuModel = new DashboardActivityModel("Reports", true, true); //Menu of Python Tutorials
        headerList.add(menuModel);
        childModel = new DashboardActivityModel("Reports Daily Transaction", false, false);
        childModelsList.add(childModel);

        childModel = new DashboardActivityModel("Purchase Report", false, false);
        childModelsList.add(childModel);

        childModel = new DashboardActivityModel("Sales Report", false, false);

        childModelsList.add(childModel);

        childModel = new DashboardActivityModel("Stock Report", false, false);
        childModelsList.add(childModel);

        childModel = new DashboardActivityModel("Account Report", false, false);
        childModelsList.add(childModel);

        childModel = new DashboardActivityModel("Quotation Report", false, false);

        childModelsList.add(childModel);

        childModel = new DashboardActivityModel("Tax Report", false, false);

        childModelsList.add(childModel);
        if (menuModel.hasChildren) {
            childList.put(menuModel, childModelsList);
        }
        menuModel = new DashboardActivityModel("Manage Leads", true, false); //Menu of Android Tutorial. No sub menus
        headerList.add(menuModel);

        if (!menuModel.hasChildren) {
            childList.put(menuModel, null);
        }
        menuModel = new DashboardActivityModel("Follow Up", true, false); //Menu of Android Tutorial. No sub menus
        headerList.add(menuModel);

        if (!menuModel.hasChildren) {
            childList.put(menuModel, null);
        }
    }

    private void populateExpandableList() {

        expandableListAdapter = new ExpandableListAdapter(this, headerList, childList);
        expandableListView.setAdapter(expandableListAdapter);

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                if (headerList.get(groupPosition).isGroup) {
                    if (!headerList.get(groupPosition).hasChildren) {
                        String model=headerList.get(groupPosition).menuName;
                        switch (model)
                        {
                            case "Dashboard":
                                loadFragment(new DashboradFragment());
                                break;
                            case "Purchase Order":
                                loadFragment(new PurchaseOrderFragment());
                                break;
                            case "Sales Bill":
                                loadFragment(new SalesBillFragment());
                                break;
                            case "Quotation":
                                loadFragment(new QuotationFragment());
                                break;
                            case "Manage Leads":
                                loadFragment(new ManageLeadsFragment());
                                break;
                            case "Follow Up":
                                loadFragment(new FollowUpFragment());
                                break;

                        }
                    }
                }

                return false;
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                if (childList.get(headerList.get(groupPosition)) != null) {
                    String model = childList.get(headerList.get(groupPosition)).get(childPosition).menuName;
                    switch (model) {


                        case "Category":
                                 loadFragment(new CategoryFragment());
                                 break;
                        case "Product":
                            loadFragment(new ProductFragment());
                            break;
                        case "Customer":
                            loadFragment(new CustomerFragment());
                            break;
                        case "Supplier":
                            loadFragment(new SupplierFragment());
                            break;
                        case "Account":
                            loadFragment(new AccountFragment());
                            break;
                        case "Tax Details":
                            loadFragment(new TaxDetailsFragment());
                            break;
                        case "Unit Details":
                            loadFragment(new UnitDetailsFragment());
                            break;
                        case "Receipts":
                            loadFragment(new ReceiptsFragment());
                            break;
                        case "Purchase Voucher":
                            loadFragment(new PurchaseVoucherFragment());
                            break;
                        case "Expense Voucher":
                            loadFragment(new ExpenseVoucherFragment());
                            break;
                        case "Report Daily Transaction":
                            loadFragment(new ReportDailyTransactionFragment());
                            break;
                        case "Purchase Report":
                            loadFragment(new PurchaseReportFragment());
                            break;
                        case "Sales Report":
                            loadFragment(new SalesReportFragment());
                            break;
                        case "Stock Report":
                            loadFragment(new StockReportFragment());
                            break;
                        case "Account Report":
                            loadFragment(new AccountFragment());
                            break;
                        case "Quotation Report":
                            loadFragment(new QuotationReportFragment());
                            break;
                        case "Tax Report":
                            loadFragment(new TaxReportFragment());
                            break;


                    }

                }

                return false;
            }
        });
    }
    private void loadFragment(Fragment fragment) {
// create a FragmentManager
        FragmentManager fm = getFragmentManager();
// create a FragmentTransaction to begin the transaction and replace the Fragment
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
// replace the FrameLayout with new Fragment
        fragmentTransaction.replace(R.id.fragment, fragment);
        fragmentTransaction.commit(); // save the changes
    }
}
