/*
* Calculator app for ECE 558 Emily Weatherford 2/12/22
*
* This app is a 6 button calculator that adds, subtracts, multiplies, divides, finds a percentage,
* and finds a square root. for the add, sub, mult, div buttons the calculator checks that both
* operands are present because both are needed for these operations. for the perc and sqrt buttons
* the calc checks that the first operand if present bc that is what is needed for the operation. If
* these aren't found the calculator shows an error message.
*
* My focus with building this was practice using classes, abstract classes, and abstract functions
*
* */


package ew22.psu.ece558.com.example.calculator

// Import
import android.os.Bundle
import android.os.Handler
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide







class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //get reuslt textview to place result in
        val result: TextView = findViewById(R.id.textView2)


        //create buttons from the buttons classes
        val multButton = MultButton()
        val divButton = DivButton()
        val addButton = AddButton()
        val subButton = SubButton()
        val percButton = PercButton()
        val sqrtButton = SqrtButton()

        //assign button views to for each button to the button's class
        multButton.view = findViewById(R.id.mult)
        divButton.view = findViewById(R.id.div)
        addButton.view = findViewById(R.id.add)
        subButton.view = findViewById(R.id.sub)
        percButton.view = findViewById(R.id.perc)
        sqrtButton.view = findViewById(R.id.sqrt)

        //add fun easter egg on long click for mult button
        multButton.view.setOnLongClickListener {
            // Use
            val handler = Handler()
            showCat()
            handler.postDelayed({
                // do something after 1000ms
                                hideCat()
            }, 3000)

        }



        //listen for button click
        multButton.view.setOnClickListener {

            val ops = getOps()
            //check operands that need to be present are
            if (multButton.twoOpsIsEmpty(ops[0], ops[1]))
                //if not pop up error text
                Toast.makeText(this, "Please enter two operands", Toast.LENGTH_SHORT).show()
            else {
                //if present assign result text to the string version of the buttons result function
                result.text = (multButton.getResult(
                    ops[0].text.toString().toDouble(),
                    ops[1].text.toString().toDouble()
                )).toString()
            }
        }

        // all buttons follow same pattern
        // really though about how I could loop through this but had trouble passing my button
        // objects and then once that worked the context for the toast needed to be passed
        // and that wasn't as straight forward as I thought it would be.
        // stuck with repeating on click logic for readability
        divButton.view.setOnClickListener {


            val ops = getOps()
            if (divButton.twoOpsIsEmpty(ops[0], ops[1])){
                Toast.makeText(this, "Please enter two operands", Toast.LENGTH_SHORT).show()
            }
            if (ops[1].text.toString().toDouble() == 0.0)
                Toast.makeText(this, "Cannot divide by 0", Toast.LENGTH_SHORT).show()
            else {
                result.text = (divButton.getResult(
                    ops[0].text.toString().toDouble(),
                    ops[1].text.toString().toDouble()
                )).toString()
            }
        }

        addButton.view.setOnClickListener {
            val ops = getOps()
            if (addButton.twoOpsIsEmpty(ops[0], ops[1]))
                Toast.makeText(this, "Please enter two operands", Toast.LENGTH_SHORT).show()
            else {
                result.text = (addButton.getResult(
                    ops[0].text.toString().toDouble(),
                    ops[1].text.toString().toDouble()
                )).toString()
            }
        }

        subButton.view.setOnClickListener {
            val ops = getOps()
            if (subButton.twoOpsIsEmpty(ops[0], ops[1]))
                Toast.makeText(this, "Please enter two operands", Toast.LENGTH_SHORT).show()
            else {
                result.text = (subButton.getResult(
                    ops[0].text.toString().toDouble(),
                    ops[1].text.toString().toDouble()
                )).toString()
            }
        }

        percButton.view.setOnClickListener {
            val ops = getOps()
            if (percButton.oneOpIsEmpty(ops[0]))
                Toast.makeText(this, "Please enter value in operand 1", Toast.LENGTH_SHORT).show()
            else {
                result.text =
                    (percButton.getResult(ops[0].text.toString().toDouble(), 0.0)).toString()
            }
        }

        sqrtButton.view.setOnClickListener {
            val ops = getOps()
            if (sqrtButton.oneOpIsEmpty(ops[0]))
                Toast.makeText(this, "Please enter value in operand 1", Toast.LENGTH_SHORT).show()
            else {
                result.text =
                    (sqrtButton.getResult(ops[0].text.toString().toDouble(), 0.0)).toString()
            }
        }

    }

    //function return operands in array
    fun getOps(): Array<EditText> {
        return arrayOf<EditText>(
            (findViewById<EditText>(R.id.Op1)),
            (findViewById<EditText>(R.id.Op2))
        )
    }

    // function to show cat, uses glide library to attach gif to image view and allow to move
    fun showCat(): Boolean {
        val cat: ImageView = findViewById(R.id.cat)
        Glide.with(this).load(R.drawable._kr).into(cat)
        return true
    }

    // function to hide cat, uses glide library to clear gif from image view
    fun hideCat(): Boolean {
        val cat: ImageView = findViewById(R.id.cat)
        Glide.with(this).clear(cat)
        return true
    }

}

// AButton has a view that will be assigned later, a results function that will be assigned later
// and two opsEmpty functions to be used depending on the operation
abstract class AButton() {

    lateinit var view: Button

    abstract fun getResult(op1: Double, op2: Double): Double

    fun twoOpsIsEmpty(op1: EditText, op2: EditText): Boolean {
        if ((op1.text.toString().trim().isEmpty()) || (op2.text.toString().trim().isEmpty())) {
            return true
        }
        return false
    }

    fun oneOpIsEmpty(op: EditText): Boolean {
        if (op.text.toString().trim().isEmpty())
            return true
        return false
    }

}

// each button class writes over the result function with the correct results operation
class MultButton() : AButton() {
    override fun getResult(op1: Double, op2: Double): Double {
        return op1 * op2
    }
}

class DivButton() : AButton() {
    override fun getResult(op1: Double, op2: Double): Double {
        return op1 / op2
    }
}

class AddButton() : AButton() {
    override fun getResult(op1: Double, op2: Double): Double {
        return op1 + op2
    }
}

class SubButton() : AButton() {
    override fun getResult(op1: Double, op2: Double): Double {
        return op1 - op2
    }
}

class PercButton() : AButton() {
    override fun getResult(op1: Double, op2: Double): Double {
        return op1 / 100
    }
}

class SqrtButton() : AButton() {
    override fun getResult(op1: Double, op2: Double): Double {
        return Math.sqrt(Math.abs(op1))
    }
}




