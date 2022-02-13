package ew22.psu.ece558.com.example.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val result: TextView = findViewById(R.id.textView2)


        val multButton = MultButton()
        val divButton = DivButton()
        val addButton = AddButton()
        val subButton = SubButton()
        val percButton = PercButton()
        val sqrtButton = SqrtButton()

        multButton.view = findViewById(R.id.mult)
        divButton.view = findViewById(R.id.div)
        addButton.view = findViewById(R.id.add)
        subButton.view = findViewById(R.id.sub)
        percButton.view = findViewById(R.id.perc)
        sqrtButton.view = findViewById(R.id.sqrt)



        multButton.view.setOnClickListener {
            val ops = getOps()
            if (multButton.twoOpsIsEmpty(ops[0], ops[1]))
                Toast.makeText(this, "Please enter two operands", Toast.LENGTH_SHORT).show()
            else {
                result.text = (multButton.getResult(
                    ops[0].text.toString().toDouble(),
                    ops[1].text.toString().toDouble()
                )).toString()
            }
        }

        divButton.view.setOnClickListener {
            val ops = getOps()
            if (divButton.twoOpsIsEmpty(ops[0], ops[1]))
                Toast.makeText(this, "Please enter two operands", Toast.LENGTH_SHORT).show()
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

    fun getOps(): Array<EditText> {
        return arrayOf<EditText>(
            (findViewById<EditText>(R.id.Op1)),
            (findViewById<EditText>(R.id.Op2))
        )
    }
}

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

//fun getButtons():Array<Button>{
//    return arrayOf<Button>(findViewById<Button>(R.id.mult),
//        findViewById<Button>(R.id.div),
//        findViewById<Button>(R.id.add),
//        findViewById<Button>(R.id.sub),
//        findViewById<Button>(R.id.perc),
//        findViewById<Button>(R.id.sqrt))
//
//}



