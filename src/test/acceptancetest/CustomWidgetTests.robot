*** Settings ***
Library         SwingLibrary
Library         keywords.CustomWidgetKeywords


*** Keywords ***

    
    
*** Test Cases ***
Widget Is Not Selected Initially
    Start Application       edu.jsu.mcis.Main
    Select Window           Main
    Label Text Should Be    label   NOT SELECTED
    Close Window            Main

HEXAGON Is Selected After Center Click
    Start Application       edu.jsu.mcis.Main
    Select Window           Main
    Click Hexagon
    Label Text Should Be    label   HEXAGON
    Close Window            Main

OCTAGON Is Selected After Center Click
    Start Application       edu.jsu.mcis.Main
    Select Window           Main
    Click OCTAGON
    Label Text Should Be    label   OCTAGON
    Close Window            Main
    
Widget Is Unchanged After Edge Click
    Start Application       edu.jsu.mcis.Main
    Select Window           Main
    Click Outside
    Label Text Should Be    label   NOT SELECTED
    Close Window            Main

Widget Toggles With Successive Center Clicks Hexagon
    Start Application       edu.jsu.mcis.Main
    Select Window           Main
    Click Hexagon
    Label Text Should Be    label   HEXAGON
    Click Hexagon
    Label Text Should Be    label   NOT SELECTED
    Close Window            Main

Widget Toggles With Successive Center Clicks Octagon
    Start Application       edu.jsu.mcis.Main
    Select Window           Main
    Click Octagon
    Label Text Should Be    label   OCTAGON
    Click Octagon
    Label Text Should Be    label   NOT SELECTED
    Close Window            Main