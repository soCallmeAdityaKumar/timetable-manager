package com.example.timetablemanager


import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.viewpager2.widget.ViewPager2
import com.example.timetablemanager.Roomdatabase.addtaskActivity
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val MaterialToolbar:MaterialToolbar=findViewById(R.id.materialtoolbar)
        val drawerlayout:DrawerLayout=findViewById(R.id.drawerlayout)
        val navigationview:NavigationView=findViewById(R.id.navigationview)




        val tablayout=findViewById<TabLayout>(R.id.tabLayout)

        val viewpager=findViewById<ViewPager2>(R.id.viewpager)

        val adapter=FragmentViewPagerAdapter(supportFragmentManager,lifecycle)
        viewpager.adapter=adapter

            val addingactionbutton=findViewById<FloatingActionButton>(R.id.AddingActionButton)
        addingactionbutton?.setOnClickListener {
            val intent = Intent(this,addtaskActivity::class.java)
            startActivity(intent)
        }



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
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.AddAccountMenu -> Toast.makeText(this,"Add Account Selected",Toast.LENGTH_LONG).show()
            R.id.SignoutMenu -> Toast.makeText(this,"Signout Selected",Toast.LENGTH_LONG).show()
            R.id.ProfileMenu -> Toast.makeText(this,"Profile Selected",Toast.LENGTH_LONG).show()
        }
        return super.onOptionsItemSelected(item)
    }

}
