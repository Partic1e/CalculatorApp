package com.example.calculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private val listForCalc = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val textView = findViewById<TextView>(R.id.textView)
        val b0 = findViewById<Button>(R.id.button_0)
        val b1 = findViewById<Button>(R.id.button_1)
        val b2 = findViewById<Button>(R.id.button_2)
        val b3 = findViewById<Button>(R.id.button_3)
        val b4 = findViewById<Button>(R.id.button_4)
        val b5 = findViewById<Button>(R.id.button_5)
        val b6 = findViewById<Button>(R.id.button_6)
        val b7 = findViewById<Button>(R.id.button_7)
        val b8 = findViewById<Button>(R.id.button_8)
        val b9 = findViewById<Button>(R.id.button_9)
        val bAC = findViewById<Button>(R.id.clear_text_button)
        val bPlusMinus = findViewById<Button>(R.id.plus_minus_button)
        val bSub = findViewById<Button>(R.id.sub_button)
        val bMul = findViewById<Button>(R.id.mul_button)
        val bMinus = findViewById<Button>(R.id.minus_button)
        val bPlus = findViewById<Button>(R.id.plus_button)
        val bEqv = findViewById<Button>(R.id.res_button)
        val bDot = findViewById<Button>(R.id.dot_button)

        b0.numberOnClick(textView)
        b1.numberOnClick(textView)
        b2.numberOnClick(textView)
        b3.numberOnClick(textView)
        b4.numberOnClick(textView)
        b5.numberOnClick(textView)
        b6.numberOnClick(textView)
        b7.numberOnClick(textView)
        b8.numberOnClick(textView)
        b9.numberOnClick(textView)
        bDot.numberOnClick(textView)

        bSub.operationOnClick(textView)
        bMul.operationOnClick(textView)
        bMinus.operationOnClick(textView)
        bPlus.operationOnClick(textView)
        bPlusMinus.negativeValueOnClick(textView)

        bAC.clearOnClick(textView)

        bEqv.resultOnClick(textView)
    }

    @SuppressLint("SetTextI18n")
    fun Button.numberOnClick(tv: TextView) {
        if (tv.text.isNotEmpty() && tv.text[0] == '0')
            tv.text = ""
        this.setOnClickListener {
            try {
                if (tv.text.isEmpty() && this.text.toString() == ",")
                    tv.text = ""
                else
                    tv.text = (tv.text.toString() + this.text.toString())
            } catch (e: Exception) {
                tv.text = "Error"
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun Button.operationOnClick(tv: TextView) {
        this.setOnClickListener {
            try {
                tv.text = tv.text.toString().replace(",", ".")
                listForCalc.add(tv.text.toString())
                listForCalc.add(this.text.toString())
                tv.text = ""
            } catch (e: Exception) {
                tv.text = "Error"
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun Button.resultOnClick(tv: TextView) {
        this.setOnClickListener {
            try {
                val result = doCalculation(tv)
                tv.text = result
            } catch (e: Exception) {
                tv.text = "Error"
            }
        }
    }

    private fun doCalculation(tv: TextView): String {
        listForCalc.add(tv.text.toString())
        val operation = listForCalc[1]
        var result = 0.0
        val intResult: Int

        return try {
            when (operation) {
                "/" -> result = listForCalc[0].toDouble() / listForCalc[2].toDouble()
                "*" -> result = listForCalc[0].toDouble() * listForCalc[2].toDouble()
                "+" -> result = listForCalc[0].toDouble() + listForCalc[2].toDouble()
                "-" -> result = listForCalc[0].toDouble() - listForCalc[2].toDouble()
            }
            if (result.toString() == result.toInt().toString()) {
                intResult = result.toInt()
                intResult.toString()
            } else {
                result.toString()
            }
        } catch (e: Exception) {
            "Error"
        }
    }

    @SuppressLint("SetTextI18n")
    fun Button.negativeValueOnClick(tv: TextView) {
        this.setOnClickListener {
            try {
                if (tv.text.isNotEmpty() && tv.text[0].toString() != "-")
                    tv.text = ("-" + tv.text.toString())
                else
                    tv.text = tv.text.toString().replace("-", "")
            } catch (e: Exception) {
                tv.text = "Error"
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun Button.clearOnClick(tv: TextView) {
        this.setOnClickListener {
            try {
                tv.text = ""
                listForCalc.clear()
            } catch (e: Exception) {
                tv.text = "Error"
            }
        }
    }
}
