package com.alexandre

import android.app.Activity
import android.widget.{Button, LinearLayout, TextView}
import android.os.Bundle

class MainActivity extends Activity {
  // allows accessing `.value` on TR.resource.constants
  implicit val context = this

  override def onCreate(savedInstanceState: Bundle): Unit = {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.main)

    val cup0: Button = findViewById(R.id.activity_main_cup0)
    val cup1: Button = findViewById(R.id.activity_main_cup1)
    val cup2: Button = findViewById(R.id.activity_main_cup2)
    val cup3: Button = findViewById(R.id.activity_main_cup3)
    val cup4: Button = findViewById(R.id.activity_main_cup4)
    val cup5: Button = findViewById(R.id.activity_main_cup5)
    val cup6: Button = findViewById(R.id.activity_main_cup6)
    val cup7: Button = findViewById(R.id.activity_main_cup7)
    val cup8: Button = findViewById(R.id.activity_main_cup8)
    val cup9: Button = findViewById(R.id.activity_main_cup9)
    val cup10: Button = findViewById(R.id.activity_main_cup10)
    val cup11: Button = findViewById(R.id.activity_main_cup11)

    val cup0b: Button = findViewById(R.id.activity_main_before_cup0)
    val cup1b: Button = findViewById(R.id.activity_main_before_cup1)
    val cup2b: Button = findViewById(R.id.activity_main_before_cup2)
    val cup3b: Button = findViewById(R.id.activity_main_before_cup3)
    val cup4b: Button = findViewById(R.id.activity_main_before_cup4)
    val cup5b: Button = findViewById(R.id.activity_main_before_cup5)
    val cup6b: Button = findViewById(R.id.activity_main_before_cup6)
    val cup7b: Button = findViewById(R.id.activity_main_before_cup7)
    val cup8b: Button = findViewById(R.id.activity_main_before_cup8)
    val cup9b: Button = findViewById(R.id.activity_main_before_cup9)
    val cup10b: Button = findViewById(R.id.activity_main_before_cup10)
    val cup11b: Button = findViewById(R.id.activity_main_before_cup11)

    val cup0bb: Button = findViewById(R.id.activity_main_bbefore_cup0)
    val cup1bb: Button = findViewById(R.id.activity_main_bbefore_cup1)
    val cup2bb: Button = findViewById(R.id.activity_main_bbefore_cup2)
    val cup3bb: Button = findViewById(R.id.activity_main_bbefore_cup3)
    val cup4bb: Button = findViewById(R.id.activity_main_bbefore_cup4)
    val cup5bb: Button = findViewById(R.id.activity_main_bbefore_cup5)
    val cup6bb: Button = findViewById(R.id.activity_main_bbefore_cup6)
    val cup7bb: Button = findViewById(R.id.activity_main_bbefore_cup7)
    val cup8bb: Button = findViewById(R.id.activity_main_bbefore_cup8)
    val cup9bb: Button = findViewById(R.id.activity_main_bbefore_cup9)
    val cup10bb: Button = findViewById(R.id.activity_main_bbefore_cup10)
    val cup11bb: Button = findViewById(R.id.activity_main_bbefore_cup11)

    val computerPlay: Button = findViewById(R.id.activity_main_computer_play_button)
    val scores: TextView = findViewById(R.id.activity_main_scores)
    val log: TextView = findViewById(R.id.activity_main_log)

    val youPlayedText: TextView = findViewById(R.id.activity_main_you_played)
    val topCupsLayoutYouPlayed: LinearLayout = findViewById(R.id.activity_main__before_top_cups)
    val bottomCupsLayoutYouPlayed: LinearLayout = findViewById(R.id.activity_main__before_bottom_cups)
    val computerPlayedText: TextView = findViewById(R.id.activity_main_computer_played)
    val topCupsLayoutComputerPlayed: LinearLayout = findViewById(R.id.activity_main_bbefore_top_cups)
    val bottomCupsLayoutComputerPlayed: LinearLayout = findViewById(R.id.activity_main_bbefore_bottom_cups)

    val holder = WidgetHolder(log, scores,
      BoardWidget(context.getResources, context.getPackageName, topCupsLayoutYouPlayed, bottomCupsLayoutYouPlayed,
        cup0b, cup1b, cup2b, cup3b, cup4b, cup5b, cup6b, cup7b, cup8b, cup9b, cup10b, cup11b),
      BoardWidget(context.getResources, context.getPackageName, topCupsLayoutComputerPlayed, bottomCupsLayoutComputerPlayed,
        cup0bb, cup1bb, cup2bb, cup3bb, cup4bb, cup5bb, cup6bb, cup7bb, cup8bb, cup9bb, cup10bb, cup11bb),
      BoardWidget(context.getResources, context.getPackageName, null, null,
        cup0, cup1, cup2, cup3, cup4, cup5, cup6, cup7, cup8, cup9, cup10, cup11),
      computerPlay,
      youPlayedText, topCupsLayoutYouPlayed, bottomCupsLayoutYouPlayed,
      computerPlayedText, topCupsLayoutComputerPlayed, bottomCupsLayoutComputerPlayed
    )

    val game = new Game(holder)
    game.setPlayListeners

  }
}