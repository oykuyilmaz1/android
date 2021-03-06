package com.cmpe451.platon.page.fragment.editprofile.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.cmpe451.platon.R
import com.cmpe451.platon.databinding.FragmentEditProfileBinding
import com.cmpe451.platon.page.activity.HomeActivity
import com.cmpe451.platon.page.fragment.editprofile.contract.EditProfileContract
import com.cmpe451.platon.page.fragment.editprofile.model.EditProfileRepository
import com.cmpe451.platon.page.fragment.editprofile.presenter.EditProfilePresenter
import com.cmpe451.platon.util.Definitions

class EditProfileFragment : Fragment(), EditProfileContract.View {

    private lateinit var binding: FragmentEditProfileBinding
    private lateinit var presenter: EditProfileContract.Presenter
    private lateinit var user : Definitions.User


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentEditProfileBinding.inflate(inflater)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        initializePresenter()
//        setUserInfo()
    }
    private fun setListeners() {
        binding.buttonEdit.setOnClickListener {
            var name:String? = null
            if(!binding.firstnameTv.text.isNullOrEmpty()){
                name  = binding.firstnameTv.text.toString()
            }
            var surname:String? = null
            if(!binding.lastnameTv.text.isNullOrEmpty()){
                surname  = binding.lastnameTv.text.toString()
            }
            var job:String? = null
            if(!binding.jobTv.text.isNullOrEmpty()){
                job  = binding.jobTv.text.toString()
            }
            var google_scholar_name:String? = null
            if(!binding.googleScholarTv.text.isNullOrEmpty()){
                google_scholar_name  = binding.googleScholarTv.text.toString()
            }
            var researchgate_name:String? = null
            if(!binding.researchGateTv.text.isNullOrEmpty()){
                researchgate_name  = binding.researchGateTv.text.toString()
            }
            presenter.onEditButtonClicked(name, surname,job, binding.privateSwitch.isChecked,null, google_scholar_name, researchgate_name)
        }
    }
//    private fun setUserInfo(){
//        user = presenter.getUser2()
//        binding.firstnameTv.setText(user.name)
//        binding.lastnameTv.setText(user.surname)
//    }
    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)

        // clear search bar, and make it icon
        val search = (menu.findItem(R.id.search_btn)?.actionView as SearchView)
        search.setQuery("", false)
        search.isIconified = true

        menu.findItem(R.id.registerFragment)?.isVisible = false
        menu.findItem(R.id.loginFragment)?.isVisible = false
        menu.findItem(R.id.search_btn)?.isVisible = false
        menu.findItem(R.id.notification_btn)?.isVisible = false
    }



    override fun initializePresenter(){
        val sharedPreferences = requireContext().getSharedPreferences("token_file", 0)
        val repository = EditProfileRepository(sharedPreferences)
        presenter = EditProfilePresenter(this, repository, sharedPreferences, (activity as HomeActivity).navController )
    }
}