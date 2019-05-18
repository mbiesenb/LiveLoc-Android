package com.liveloc.view

import android.Manifest
import android.content.Context
import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_live_loc_view.*
import kotlinx.android.synthetic.main.app_bar_live_loc.*
import com.google.android.gms.maps.SupportMapFragment
import com.liveloc.location.GpsLocation
import android.content.pm.PackageManager
import android.util.Log
import android.view.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.liveloc.R
import com.liveloc.view.mapview.GoogleMaps
import com.liveloc.view.mapview.MapViewInterface
import com.liveloc.model.group.Group
import com.liveloc.viewmodel.GroupViewModel
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.view.menu.MenuBuilder
import androidx.recyclerview.widget.RecyclerView
import com.liveloc.view.Adapter.GroupListAdapter


class LiveLoc : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    /*
        Recycler View Stuff
     */
    var groups: MutableList<Group> = mutableListOf()
    lateinit var groupListAdapter: ArrayAdapter<Group>


    lateinit var groupViewModel: GroupViewModel
    lateinit var mapView: MapViewInterface
    lateinit var gpsLocation: GpsLocation

    /**
     *  Screencomponents
     */
    lateinit var supportMapFragment: SupportMapFragment
    lateinit var groupListView: ListView
    lateinit var createGroupBtn: Button
    lateinit var addGroupBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_loc_view)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        //nav_view.setNavigationItemSelectedListener(this)


        /*
            initializazion of Group Repository
         */
        /*
            Initializiation of Tools
            Order is important !
         */
        initScreenComponents()
        initObervers()
        initClickListener()
        initGoogleMaps()
        initGps()

        showAddGroupPopUp()

    }

    fun initClickListener() {
        createGroupBtn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                showCreateGroupPopUp()
            }
        })
        addGroupBtn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                showAddGroupPopUp()
            }
        })

    }

    private fun initObervers() {
        try {
            groupViewModel = ViewModelProviders.of(this).get(GroupViewModel::class.java)
        } catch (exception: Exception) {
            Log.d("GROUP", exception.toString())
        }
        groupViewModel.getAll().observe(this, object : Observer<List<Group>> {
            override fun onChanged(groups: List<Group>) {
                updateGroupList(groups)
            }
        })
    }

    private fun initScreenComponents() {
        supportMapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        groupListView = findViewById<ListView>(R.id.group_list_view)
        createGroupBtn = findViewById<Button>(R.id.create_group_btn)
        addGroupBtn = findViewById<Button>(R.id.add_group_btn)

        groupListAdapter = GroupListAdapter(this, groups)
        groupListView.adapter = groupListAdapter
    }

    private fun initGoogleMaps() {
        mapView = GoogleMaps(supportMapFragment)
    }

    private fun initGps() {
        ActivityCompat.requestPermissions(this, arrayOf<String>(Manifest.permission.ACCESS_FINE_LOCATION), 1);
    }

    /*
        TODO: handle permission denied
     */
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            1 -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    gpsLocation = GpsLocation(this, mapView)
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return
            }
        }// other 'case' lines to check for other
        // permissions this app might request
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.live_loc, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return true
    }

    fun updateGroupList(groups: List<Group>) {
        this.groups.clear()
        this.groups = groups as MutableList<Group>
        this.groupListAdapter.addAll(groups)
    }


    fun showCreateGroupPopUp() {
        /*LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
View popupInputDialogView = layoutInflater.inflate(R.layout.popup_input_dialog, null);*/
        var alertDialogBuilder = AlertDialog.Builder(this);
        // Set title, icon, can not cancel properties.
        alertDialogBuilder.setTitle("Add new group");
        //alertDialogBuilder.setIcon(R.drawable.ic_launcher_background);
        alertDialogBuilder.setCancelable(true);
        var layoutInflater = LayoutInflater.from(this)
        var popupInputDialogView = layoutInflater.inflate(R.layout.create_group_view, null)
        alertDialogBuilder.setView(popupInputDialogView)
        var alertDialog: AlertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    fun showAddGroupPopUp() {
        /*LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
View popupInputDialogView = layoutInflater.inflate(R.layout.popup_input_dialog, null);*/
        var alertDialogBuilder = AlertDialog.Builder(this);
        // Set title, icon, can not cancel properties.
        alertDialogBuilder.setTitle("Add new group");
        //alertDialogBuilder.setIcon(R.drawable.ic_launcher_background);
        alertDialogBuilder.setCancelable(true);
        var layoutInflater = LayoutInflater.from(this)
        var popupInputDialogView = layoutInflater.inflate(R.layout.add_group_view, null)
        alertDialogBuilder.setView(popupInputDialogView)
        var alertDialog: AlertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


    companion object {
        //private val INTENT_USER_ID = "user_id"
        fun newIntent(context: Context): Intent {
            val intent = Intent(context, LiveLoc::class.java)
            return intent
        }
    }

}
