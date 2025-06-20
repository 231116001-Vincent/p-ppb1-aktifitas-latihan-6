package com.vharya.drawernavassignment

import android.os.Bundle
import android.view.MenuItem
import android.widget.LinearLayout
import androidx.activity.addCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.setPadding
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.vharya.drawernavassignment.fragments.AbsenceFragment
import com.vharya.drawernavassignment.fragments.BillsFragment
import com.vharya.drawernavassignment.fragments.GalleryFragment
import com.vharya.drawernavassignment.fragments.GradeFragment
import com.vharya.drawernavassignment.fragments.HomeFragment
import com.vharya.drawernavassignment.fragments.SettingsFragment
import com.vharya.drawernavassignment.fragments.ToolsFragment
import com.vharya.drawernavassignment.fragments.SlideshowFragment

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var mainLayout: LinearLayout
    private lateinit var toolbarView: Toolbar
    private lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.main)
        mainLayout = findViewById(R.id.content_container)
        toolbarView = findViewById(R.id.toolbar)
        navigationView = findViewById(R.id.nav_view)

        ViewCompat.setOnApplyWindowInsetsListener(mainLayout) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        ViewCompat.setOnApplyWindowInsetsListener(navigationView) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setSupportActionBar(toolbarView)

        navigationView.setNavigationItemSelectedListener(this)

        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbarView, R.string.open_nav, R.string.close_nav)

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container, HomeFragment()
            ).commit()
            navigationView.setCheckedItem(R.id.nav_home)
        }

        onBackPressedDispatcher.addCallback {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START)
            }
            else {
                onBackPressedDispatcher.onBackPressed()
            }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragment()).commit()

            R.id.nav_absence -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, AbsenceFragment()).commit()

            R.id.nav_grade -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, GradeFragment()).commit()

            R.id.nav_bills -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, BillsFragment()).commit()

            R.id.nav_settings -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, SettingsFragment()).commit()
        }

        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}