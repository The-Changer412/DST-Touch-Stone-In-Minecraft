./gradlew build
cd build/libs
rm ~/.local/share/multimc/instances/'Fabric 1.18.2'/.minecraft/mods/Touch_Stone_DST-MOD-1.0.0_MC-1.18.2.jar
cp Touch_Stone_DST-MOD-1.0.0_MC-1.18.2.jar ~/.local/share/multimc/instances/'Fabric 1.18.2'/.minecraft/mods
cd ~/.local/share/multimc
./MultiMC -l "Fabric 1.18.2"
