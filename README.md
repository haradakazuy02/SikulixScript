# SikulixScript

    It adds sikulix function over the ScriptShell.(javascript/scala)

    e.g. sikulix-script -script scriptfileOfScala scala

    e.g. sikulix-script -i javascript

## usage

- Install Simple Build Tool

- Build

    sbt pack

- Command

    target\pack\bin\sikulix-script (options) \[language] (name1=value1) (name2=value2)..

    - options
        -script \[scriptfile] : run script file
        -i : interpreter after running the [scriptfile]
        -init \[line] : run the script \[line] before start \[scriptfile]

    - language
        -scala : scala script
        -javascript : java script

    - name=value
        set the value to the variable for the name.

## script

    - function

        Some of org.sikuli.script.Screen methods can be used.

        e.g. if (find(pathOfPng)) click(targetPng);

