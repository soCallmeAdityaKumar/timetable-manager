package com.example.timetablemanager


import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.timetablemanager.futuretask.FutureTask
import com.example.timetablemanager.futuretask.FutureTaskRecyclerViewAdapter
import com.google.android.material.internal.ContextUtils.getActivity
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tablayout=findViewById<TabLayout>(R.id.tabLayout)

        val viewpager=findViewById<ViewPager2>(R.id.viewpager)

        val adapter=FragmentViewPagerAdapter(supportFragmentManager,lifecycle)
        viewpager.adapter=adapter





        TabLayoutMediator(tablayout,viewpager){
            tab,position->
            when(position){
                0->{
                    tab.text="Future Task"
                }
                1->{
                    tab.text="Postponed Task"
                }
                2->{
                    tab.text="Goal Status"
                }
            }

        }.attach()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()||super.onSupportNavigateUp()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.AddAccount -> Toast.makeText(this,"Add Account Selected",Toast.LENGTH_SHORT).show()
            R.id.Signout -> Toast.makeText(this,"Signout Selected",Toast.LENGTH_SHORT).show()
            R.id.Profile -> Toast.makeText(this,"Profile Selected",Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }
}
