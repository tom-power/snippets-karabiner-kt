### Text expansion for MacOS using [Karabiner elements](https://karabiner-elements.pqrs.org/).

Text expansion/replacement similar to [espanso](https://espanso.org/) etc using [karabiner elements](https://karabiner-elements.pqrs.org/)

Was an experiment trying out [karabiner-kt](https://github.com/kaushikgopal/karabiner-kt) but seems to work \o/

```shell
nano /path/to/snippetRules.json # see testSnippetRules.json for example
clone https://github.com/tom-power/snippets-karabiner-kt.git
cd snippets-karabiner-kt
./gradlew run --args="/path/to/snippetRules.json"
cp ./app/build/*.json ~/.config/karabiner/assets/complex_modifications/
```

The rules will need to be loaded in the Karabiner elements UI:

```"Preferences" -> "Complex Modifications" -> "Add rule" -> "snippets ..." -> "Enable all"```

To make a change update `snippetRules.json`, then `./gradlew run --args="/path/to/snippetRules.json"`, then remove and add all rules.

