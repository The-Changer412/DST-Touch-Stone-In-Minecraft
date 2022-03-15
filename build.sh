./gradlew build
cd build/libs
rm ~/.local/share/multimc/instances/'Fabric 1.18.1'/.minecraft/mods/fabric-example-mod-1.0.0.jar
rm ~/.local/share/multimc/instances/'Fabric 1.18.1'/.minecraft/mods/fabric-example-mod-1.0.0-sources.jar
cp fabric-example-mod-1.0.0.jar ~/.local/share/multimc/instances/'Fabric 1.18.1'/.minecraft/mods
cp fabric-example-mod-1.0.0-sources.jar ~/.local/share/multimc/instances/'Fabric 1.18.1'/.minecraft/mods
cd ~/.local/share/multimc
./MultiMC -l "Fabric 1.18.1"
