./gradlew build
cd build/libs
rm ~/.local/share/multimc/instances/'Fabric 1.18.2'/.minecraft/mods/touchstonedst-1.0.0.jar
rm ~/.local/share/multimc/instances/'Fabric 1.18.2'/.minecraft/mods/touchstonedst-1.0.0-sources.jar
cp touchstonedst-1.0.0.jar ~/.local/share/multimc/instances/'Fabric 1.18.2'/.minecraft/mods
cp touchstonedst-1.0.0-sources.jar ~/.local/share/multimc/instances/'Fabric 1.18.2'/.minecraft/mods
cd ~/.local/share/multimc
./MultiMC -l "Fabric 1.18.2"
