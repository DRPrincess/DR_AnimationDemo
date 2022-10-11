package com.dr.anim

import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.Scene
import android.transition.TransitionManager
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_transition.*

class TransitionActivity : AppCompatActivity() {
    private var isScene1 = true
    private lateinit  var scene1:Scene
    private lateinit  var scene2:Scene
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transition)

        button_first.setOnClickListener {
            changeScene()
        }

        //变换前后的两个场景
        scene1 = Scene.getSceneForLayout(fl_scene_root, R.layout.layout_scene_origin, this)
        scene2 = Scene.getSceneForLayout(fl_scene_root, R.layout.layout_scene_end, this)

        //默认先展示场景1
        TransitionManager.go(scene1)
        isScene1 = true
    }

    private fun changeScene() {
        val toScene = if (isScene1) scene2 else scene1
        isScene1 = !isScene1
        val changeBounds = ChangeBounds()
        TransitionManager.go(toScene, changeBounds)
    }
}