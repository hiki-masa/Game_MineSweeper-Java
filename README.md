# Javaによるマインスイーパー実装

## マインスイーパーの遊び方
ゲーム画面は正方形のマスが敷き詰められた長方形のフィールドから構成されている。
マウスで指定したマスを左クリックすることで開けることができるが、地雷の置かれているマスを開けると Game Over となる。
地雷の置かれていないマスを開けたときは、隣接する8方向のマスのいずれかに地雷がある場合はその個数が表示され、隣接するマスに地雷が置かれていないときは、それらが自動的に開けられる。
地雷の置かれていないマスをすべて開ければ勝ちとなる。
プレイヤーは、地雷が置かれていると思われるマスをマウスの右クリックすることで旗を立てることができる。
(Wikipedia参照)

- スタート時
![image](https://user-images.githubusercontent.com/78514639/158332124-ad798dcc-f484-41c0-89f2-6b8b72cad993.png)

- 途中画面
![image](https://user-images.githubusercontent.com/78514639/158333792-e9f1f31c-344d-4801-a1f3-2185d67f05c4.png)

- すべての地雷に旗を立てることができると Game Clear
![image](https://user-images.githubusercontent.com/78514639/158335177-5cebba57-7a21-4b54-bb5a-c4b60523e740.png)

- 地雷マスを開けた Game Over (すべてのマスが開けられ，答えが表示される)
![image](https://user-images.githubusercontent.com/78514639/158335470-a35bfbf1-5808-48fd-9ceb-599bca6e16ca.png)