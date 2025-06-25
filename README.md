# Text expansion for MacOS using [Karabiner elements](https://karabiner-elements.pqrs.org/).

Text expansion/replacement similar to [espanso](https://espanso.org/) etc using [karabiner elements](https://karabiner-elements.pqrs.org/)

Was an experiment trying out [karabiner-kt](https://github.com/kaushikgopal/karabiner-kt) but seems to work \o/

![demo](https://github.com/tom-power/snippets-karabiner-kt/blob/main/assets/demo.gif)

## Installation

```shell
clone https://github.com/tom-power/snippets-karabiner-kt.git
cd snippets-karabiner-kt
./gradlew run --args="/path/to/snippetRules.json"
cp ./app/build/snippets-karabiner.json ~/.config/karabiner/assets/complex_modifications/
```

Example `snippetRules.json`:

```json
{
  "rules": [
    {
      "description": "snippets - fab -> foo@bar.com",
      "keys": "fab",
      "replacement": "foo@bar.com"
    }
  ]
}
```

The rules will need to be loaded in the Karabiner elements UI:

```"Preferences" -> "Complex Modifications" -> "Add rule" -> "snippets ..." -> "Enable all"```

To make a change update `snippetRules.json`, then `./gradlew run --args="/path/to/snippetRules.json"`, then remove and add all rules.

## Usage

Type the `keys` configured in `snippetRules.json` and press space, they should be replaced by the `replacement`.