package br.edu.ifsp.scl.ads.sendmail

import android.content.Intent
import android.content.Intent.*
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.edu.ifsp.scl.ads.sendmail.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val amb: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root)

        //botão cleaner
        amb.cleanBt.setOnClickListener {
            with(amb) {
                toEt.setText("")
                ccEt.setText("")
                bccEt.setText("")
                subjectEt.setText("")
                messageEt.setText("")
            }
        }

        amb.sendBt.setOnClickListener {
            val sendMailIntent = Intent(ACTION_SENDTO)

            with (sendMailIntent) {
                putExtra(EXTRA_EMAIL, arrayOf(amb.toEt.text.toString()))
                putExtra(EXTRA_CC, arrayOf(amb.ccEt.text.toString()))
                putExtra(EXTRA_BCC, arrayOf(amb.bccEt.text.toString()))
                putExtra(EXTRA_SUBJECT, amb.subjectEt.text.toString())
                putExtra(EXTRA_TEXT, amb.messageEt.text.toString())
                //qual documento e como a msg deve ser
                type = "message/rfc822"
                //prefixo
                data = Uri.parse("mailto:")
            }

            val chooserIntent = Intent(ACTION_CHOOSER)
            chooserIntent.putExtra(EXTRA_INTENT, sendMailIntent)
            startActivity(chooserIntent)
        }
    }
}