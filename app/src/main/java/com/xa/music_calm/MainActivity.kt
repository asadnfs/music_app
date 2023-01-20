package com.xa.music_calm

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var run: Runnable
    private var handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        arrow_blow.setOnClickListener {
            val a = Intent(this, list_main::class.java)
            startActivity(a)
        }

        val media = MediaPlayer.create(this, R.raw.music)
        seek_bar.progress = 0
        seek_bar.max = media.duration


        view.setOnClickListener {
            if (!media.isPlaying) {
                media.start()

                view.setImageResource(R.drawable.resume_ic)
            } else {
                media.pause()
                view.setImageResource(R.drawable.pause_3)

            }
        }
        seek_bar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    media.seekTo(progress)


                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })

        run = Runnable {
            seek_bar.progress = media.currentPosition
            handler.postDelayed(run, 1000)
        }

        handler.postDelayed(run, 1000)

        media.setOnCompletionListener {
            view.setImageResource(R.drawable.pause_3)
            seek_bar.progress = 0

        }

    }


}
