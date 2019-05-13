package com.liveloc.view

import android.Manifest
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_live_loc_view.*
import kotlinx.android.synthetic.main.app_bar_live_loc.*
import com.google.android.gms.maps.SupportMapFragment
import com.liveloc.location.GpsLocation
import android.content.pm.PackageManager
import android.util.Log
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
import android.view.MenuInflater
import androidx.appcompat.view.menu.MenuBuilder




class LiveLoc : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    lateinit var groupViewModel : GroupViewModel
    lateinit var mapView: MapViewInterface
    lateinit var gpsLocation: GpsLocation

    /**
     *  Screencomponents
     */
    lateinit var grouMenu : Menu
    lateinit var supportMapFragment : SupportMapFragment

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

        nav_view.setNavigationItemSelectedListener(this)


        /*
            initializazion of Group Repository
         */
        try {
            groupViewModel = ViewModelProviders.of(this).get(GroupViewModel::class.java)
        }catch (exception : Exception){
            Log.d("GROUP", exception.toString())
        }
        groupViewModel.getAll().observe(this, object : Observer<List<Group>> {
            override fun onChanged(groups: List<Group>){
                updateGroupList(groups)
            }
        })

        /*
            Initializiation of Tools
            Order is important !
         */
        initScreenComponents()
        initGoogleMaps()
        initGps()

    }

    private fun initScreenComponents() {
        supportMapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment

        //grouMenu = findViewById(R.id.group_menu)

    }

    private fun initGoogleMaps() {
       mapView = GoogleMaps(supportMapFragment)
    }

    private fun initGps() {
        ActivityCompat.requestPermissions(this,arrayOf<String>( Manifest.permission.ACCESS_FINE_LOCATION), 1);
    }

    /*
        TODO: handle permission denied
     */
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            1 -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    gpsLocation = GpsLocation(this,mapView)
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

        //menu.add(0, 0, 0, "Option1").setShortcut('3', 'c');
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
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    fun updateGroupList(groups : List<Group>){
        val menu = MenuBuilder(this)
        MenuInflater(this).inflate(R.menu.activity_live_loc_view_drawer, menu)
        for( group in groups){
            Log.d("GROUP" , group.name)
            //menu.add(group.toString())
            menu.add(0,0,0,"Menu")?.setIcon(R.drawable.com_facebook_button_icon)?.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
        }
    }


    companion object {
        //private val INTENT_USER_ID = "user_id"
        fun newIntent(context: Context): Intent {
            val intent = Intent(context, LiveLoc::class.java)
            return intent
        }
    }

}
