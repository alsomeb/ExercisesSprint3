<h1>JComponent</h1>

<h2> Till exempel JTextField, JTextArea, JScrollPane, JCheckBox, JRadioButton, JComboBox</h2>
<p>Alla ärver från JComponent typ setBackGround, setForeGround, setFont, setOpaque(genomskinligt),
- setEnabled, setVisible, setSize, setPreferredSize</p>

- revalidate(), begär att barnkomponenterna placeras om, om man flyttat om komponenter
- repaint() uppdaterar GUI:t, gör till ovan också oftast
- getParent() - som den säger
- add() lägger ut
- getComponent(i) returnerar komponenten på plats i
- removeComponent(i) tar bort på plats i
- removeAll()
- setLayout() anger layout