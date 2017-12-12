package example

import scala.swing._
import scala.swing.event.ButtonClicked
import java.awt.Color

object CalcMain extends SimpleSwingApplication {
  override def top = new MainFrame {
    title = "電卓"
    minimumSize = new Dimension(400, 500)
    contents = gridBagPanel
  }
  
  val gridBagPanel = new GridBagPanel() {
    // ボタンのテキストと表示位置
    val buttonMap = Map (
      0 -> ("0", pair2Constraints(0, 5)),
      1 -> ("1", pair2Constraints(0, 4)),
      2 -> ("2", pair2Constraints(1, 4)),
      3 -> ("3", pair2Constraints(2, 4)),
      4 -> ("4", pair2Constraints(0, 3)),
      5 -> ("5", pair2Constraints(1, 3)),
      6 -> ("6", pair2Constraints(2, 3)),
      7 -> ("7", pair2Constraints(0, 2)),
      8 -> ("8", pair2Constraints(1, 2)),
      9 -> ("9", pair2Constraints(2, 2)),
      10 -> ("+", pair2Constraints(3, 4)),
      11 -> ("-", pair2Constraints(3, 3)),
      12 -> ("×", pair2Constraints(3, 2)),
      13 -> ("÷", pair2Constraints(3, 1)),
      14 -> ("AC", pair2Constraints(0, 1)),
      15 -> ("+/-", pair2Constraints(1, 1)),
      16 -> ("%", pair2Constraints(2, 1)),
      17 -> (".", pair2Constraints(2, 5)),
      18 -> ("=", pair2Constraints(3, 5))
    )
    
    // ボタンの生成
    val buttonArray = new Array[Button](20)
    for (buttonNum <- 0 until buttonMap.size) {
      buttonArray(buttonNum) = new Button(buttonMap(buttonNum)._1) {
        buttonNum match {
          case 0 => {
            preferredSize = new Dimension(200, 50)
            buttonMap(buttonNum)._2.gridwidth_=(2)
          }
          case _ => preferredSize = new Dimension(100, 50)
        }
        
        reactions += {
          case e: ButtonClicked => texArea.text_=(e.source.text)
        }
      }
      layout += buttonArray(buttonNum) -> buttonMap(buttonNum)._2
    }
    
    // 計算結果を表示するラベルを生成
    val a = pair2Constraints(0, 0)
    a.gridwidth = 4
    val texArea = new TextArea {
      preferredSize = new Dimension(400, 50)
      background = Color.white
    }
    texArea.editable_=(false)
    layout += texArea -> a
  }
}
