package com.ibtikar.apps.task.ui.profile

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ibtikar.apps.task.R
import com.ibtikar.apps.task.api.ActorsClient
import com.ibtikar.apps.task.api.ActorsHelper
import com.ibtikar.apps.task.pojo.ActorsProfiles
import com.ibtikar.apps.task.ui.detais.DetailsImagesActivity
import com.ibtikar.apps.task.ui.factory.ViewModelFactory
import com.ibtikar.apps.task.ui.main.MainActivity
import com.ibtikar.apps.task.utils.ConstantsUtils
import com.ibtikar.apps.task.utils.Status
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {

    private var id = 0

    companion object {
        private const val TAG = "ProfileActivity"
    }

    private lateinit var adapter: GridAdapter
    private lateinit var viewModel: ProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        setView()
        getProfileData()

        gridViewID.onItemClickListener = OnItemClickListener { parent, view, position, id ->
            sendImage(parent, position)
        }
    }

    private fun sendImage(parent: AdapterView<*>?, position: Int) {
        val item = parent?.getItemAtPosition(position) as ActorsProfiles.Profile
        val intent = Intent(this, DetailsImagesActivity::class.java)
        intent.putExtra("image", item.filePath)
        startActivity(intent)
    }

    private fun setView() {

        val intent = intent
        id = intent.extras!!.getInt("id")

        viewModel = ViewModelProviders.of(
            this, ViewModelFactory(ActorsHelper(ActorsClient.actorsInterface))
        ).get(ProfileViewModel::class.java)

        val typeface = Typeface.createFromAsset(assets, "fonts/fontfamily.ttf")
        detailsId.typeface = typeface
        nameID.typeface = typeface
        genderID.typeface = typeface
        departID.typeface = typeface

        profileNameID.text = intent.extras?.getString("Name")

        val gender = intent.extras!!.getInt("Gender")
        if (gender == 1) {
            profileGenderID.text = "Female"
        } else {
            profileGenderID.text = "Male"
        }

        profileDepartmentId.text = intent.extras?.getString("depart")
    }

    private fun getProfileData() {

        viewModel.getProfilesImg(id, ConstantsUtils.API_KEY).observe(this, Observer {

            it?.let { resource ->
                when (resource.status) {

                    Status.SUCCESS -> {
                        resource.data?.let { result ->
                            result.profiles?.let { list ->
                                retrieveList(
                                    list
                                )
                            }
                        }
                    }
                    Status.ERROR -> {
                        Log.d(TAG, "Error" + it.message)
                    }
                    Status.LOADING -> {
                        //Toast.makeText(this, "Please Wait", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    private fun retrieveList(list: List<ActorsProfiles.Profile>) {
        adapter = GridAdapter(this, R.layout.custom_gridview_layout, list)
        gridViewID.adapter = adapter
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this@ProfileActivity, MainActivity::class.java))
    }

}