package m.m.androidlab

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import m.m.androidlab.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.round

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculate.setOnClickListener { calculateTip() }
    }

    private fun calculateTip() {
        try {
            val costOfService: Double =  binding.costOfService.text.toString().toDouble()
            val tipPercentage = when (binding.serviceTipOptions.checkedRadioButtonId) {
                R.id.option_twenty_percent -> 0.2
                R.id.option_eighteen_percent -> 0.18
                else -> 0.15
            }

            var tip = costOfService * tipPercentage

            if (binding.roundUpTipSwitch.isChecked) {
                tip = round(tip)
            }
            displayTip(tip)
        } catch (ex: Throwable) {
//            Toast.makeText(applicationContext, "error: ${ex.localizedMessage}", Toast.LENGTH_LONG).show()
            binding.tipResult.text = getString(R.string.tip_result, NumberFormat.getCurrencyInstance().format(0.0))
        }
    }

    private fun displayTip(tip: Double) {
        binding.tipResult.text = getString(R.string.tip_result, NumberFormat.getCurrencyInstance().format(tip))
    }
}