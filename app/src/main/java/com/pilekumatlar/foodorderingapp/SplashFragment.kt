package com.pilekumatlar.foodorderingapp

import android.animation.Animator
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.airbnb.lottie.LottieAnimationView

class SplashFragment : Fragment() {

    lateinit var lottieView: LottieAnimationView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Handler(Looper.getMainLooper()).postDelayed({
        findNavController().navigate(R.id.action_splashFragment_to_addFoodFragment2)
        }, 5000)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lottieView = view.findViewById(R.id.animation_view)
        lottieView.addAnimatorListener(object: Animator.AnimatorListener {

            override fun onAnimationStart(animation: Animator?) {
                Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show()
            }

            override fun onAnimationEnd(animation: Animator?) {
                Toast.makeText(context, "End", Toast.LENGTH_SHORT).show()
            }

            override fun onAnimationCancel(animation: Animator?) {
                Toast.makeText(context, "Cancel", Toast.LENGTH_SHORT).show()
            }

            override fun onAnimationRepeat(animation: Animator?) {
                Toast.makeText(context, "Repeat", Toast.LENGTH_SHORT).show()
            }

        })
    }

}