package com.dimensiva.musicapp.fragments

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import com.dimensiva.musicapp.R
import com.dimensiva.musicapp.databinding.FragmentMusicBinding

class MusicFragment : Fragment() {

    private lateinit var mp: MediaPlayer
    private var totalTime: Int = 0


    private lateinit var binding: FragmentMusicBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMusicBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        fun newInstance(): MusicFragment {
            return MusicFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        mp = MediaPlayer.create(activity, R.raw.music)
        mp.isLooping = false
        mp.setVolume(0.5f, 0.5f)
        totalTime = mp.duration


        // Volume Bar
        binding.volumeBar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekbar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    if (fromUser) {
                        var volumeNum = progress / 100.0f
                        mp.setVolume(volumeNum, volumeNum)
                    }
                }

                override fun onStartTrackingTouch(p0: SeekBar?) {
                }

                override fun onStopTrackingTouch(p0: SeekBar?) {
                }
            }
        )


        // Position Bar
        binding.apply {
            positionBar.max = totalTime

            positionBar.setOnSeekBarChangeListener(
                object : SeekBar.OnSeekBarChangeListener {
                    override fun onProgressChanged(
                        seekBar: SeekBar?,
                        progress: Int,
                        fromUser: Boolean
                    ) {
                        if (fromUser) {
                            mp.seekTo(progress)
                        }
                    }

                    override fun onStartTrackingTouch(p0: SeekBar?) {
                    }

                    override fun onStopTrackingTouch(p0: SeekBar?) {
                    }
                }
            )
        }

        @SuppressLint("HandlerLeak")
        var handler = object : Handler() {
            override fun handleMessage(msg: Message) {
                var currentPosition = msg.what

                // Update positionBar
                binding.positionBar.progress = currentPosition

                // Update Labels
                var elapsedTime = createTimeLabel(currentPosition)
                binding.elapsedTimeLabel.text = elapsedTime


                var remainingTime = createTimeLabel(totalTime - currentPosition)
                binding.remainingTimeLabel.text = "-$remainingTime"

            }
        }


        // Thread
        Thread(Runnable {
            while (mp != null) {
                try {
                    var msg = Message()
                    msg.what = mp.currentPosition
                    handler.sendMessage(msg)
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                }
            }
        }).start()

        binding.playBtn.setOnClickListener {
            if (mp.isPlaying) {
                // Stop
                binding.animationView.pauseAnimation()
                mp.pause()
                binding.playBtn.setBackgroundResource(R.drawable.play)

            } else {
                // Start
                binding.animationView.playAnimation()
                mp.start()
                binding.playBtn.setBackgroundResource(R.drawable.stop)
            }
        }

    }

    fun createTimeLabel(time: Int): String {
        var timeLabel = ""
        var min = time / 1000 / 60
        var sec = time / 1000 % 60

        timeLabel = "$min:"
        if (sec < 10) timeLabel += "0"
        timeLabel += sec

        return timeLabel
    }


}