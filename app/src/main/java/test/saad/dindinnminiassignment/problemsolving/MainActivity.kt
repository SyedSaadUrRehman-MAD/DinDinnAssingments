package test.saad.dindinnminiassignment.problemsolving

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import test.saad.dindinnminiassignment.R
import test.saad.dindinnminiassignment.assingmenttask.ui.HomeActivity
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity() {
    private val letters: String = "abcdefghijklmnopqrstuvwxyz"
    private lateinit var outputLog: String
    private lateinit var output: String
    private lateinit var outLog: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun moveInput(view: View) {
        if (validateInput()) {
            outputLog = "Output:\n"
            output = ""
            outLog = ""
            calOutput()
        } else
            Snackbar.make(view, "Invalid input!", Snackbar.LENGTH_SHORT);
    }

    private fun calOutput() {
        try {
            val input = inputString.editText?.text!!.toString()
            val steps = Integer.parseInt(inputSteps.editText?.text!!.toString())
            for (i in 0..input.length - 1) {
                val character = input[i]
                val isCaps = character != character.toLowerCase()
                val indexOfChar = letters.indexOf(character.toLowerCase())
                if (steps < letters.length)
                    moveChar(indexOfChar, steps, isCaps)
                else
                    moveChar(indexOfChar, steps % letters.length, isCaps)
            }
            tvOutputDesc.text = outputLog + output + "\n" + outLog
        }catch (e:NumberFormatException)
        {
            Toast.makeText(this,"Incorrect input",Toast.LENGTH_LONG).show()
        }
    }

    private fun moveChar(indexOfChar: Int, steps: Int, isCaps: Boolean) {
        val character = letters[indexOfChar]
        if (indexOfChar + steps < letters.length) {
            val shiftedChar =
                if (isCaps) letters[indexOfChar + steps].toUpperCase() else letters[indexOfChar + steps]
            output += shiftedChar
            outLog += letters[indexOfChar] + " is moved by " + steps + " times which result in character " + shiftedChar + "\n"
        } else {
            val shiftedChar =
                if (isCaps) letters[indexOfChar + steps - letters.length].toUpperCase() else letters[indexOfChar + steps - letters.length]
            output += shiftedChar
            outLog += character + " is moved by " + steps + " times which result in character " + shiftedChar + "\n"
        }
    }

    private fun validateInput(): Boolean {
        return inputString.editText?.text?.length!! > 0 && inputSteps.editText?.text?.length!! > 0
    }

    fun navigateToAssignment(view: View) {
        startActivity(Intent(this, HomeActivity::class.java))
    }
}