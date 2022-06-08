package com.example.uniclima

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.uniclima.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class Desenvolvedores : Fragment(),View.OnClickListener {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    val button_ryan: Button? = null
    val button_tolenta: Button? = null
    val button_fernando: Button? = null


    var navTolentino: NavController? = null
    var navRyan: NavController? = null
    var navFernando: NavController? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        navRyan = Navigation.findNavController(view)
        view.findViewById<Button>(R.id.button_ryan)?.setOnClickListener(this)


        navFernando = Navigation.findNavController(view)
        view.findViewById<Button>(R.id.button_fernando)?.setOnClickListener(this)

        navTolentino = Navigation.findNavController(view)
        view.findViewById<Button>(R.id.button_tolenta)?.setOnClickListener(this)

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(view: View) {

        var btSelecionado =view as Button
        var cellId = 0
        when(btSelecionado.id){
            R.id.button_ryan -> cellId=1
            R.id.button_tolenta->cellId=2
            R.id.button_fernando->cellId=3
        }
        if(cellId==1){
            navRyan?.navigate(R.id.action_Desenvolverores_to_ryan)
        }else if(cellId==2){
            navTolentino?.navigate(R.id.action_Desenvolverores_to_tolentino)
        }else{
            navFernando?.navigate(R.id.action_Desenvolverores_to_fernando)
        }


    }

}