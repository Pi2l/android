package m.m.androidlab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

const val KEY_ROLLED_DICE = "rolled_dice"

class MainActivity : AppCompatActivity() {
    private var rolledDice: Int = 6

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            rolledDice = savedInstanceState.getInt(KEY_ROLLED_DICE, 6)
        }

        findViewById<ImageView>(R.id.imageView).setImageResource(getImageToDraw())

        val rollerButton: Button = findViewById(R.id.button)
        rollerButton.setOnClickListener { rollDice() }
    }

    private fun rollDice() {
        val rolledImage: ImageView = findViewById(R.id.imageView)
        rolledImage.setImageResource(R.drawable.dice_1)

        rolledDice = Dice().roll()
        var imageToDraw: Int = getImageToDraw()
        rolledImage.contentDescription = rolledDice.toString()
        rolledImage.setImageResource(imageToDraw)

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_ROLLED_DICE, rolledDice)
    }

    private fun getImageToDraw(): Int {
        return when (rolledDice) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> { R.drawable.dice_6 }
        }
    }
}

