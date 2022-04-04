call gradlew build
echo FINISH BUILDING
del C:\Users\Owner\Documents\MultiMC\instances\"Fabric 1.18.2"\.minecraft\mods\Touch_Stone_DST-MOD-1.0.0_MC-1.18.2.jar
echo DELETED THE OLD MOD
copy C:\Users\Owner\Documents\github\DST-Touch-Stone-In-Minecraft\build\libs\Touch_Stone_DST-MOD-1.0.0_MC-1.18.2.jar C:\Users\Owner\Documents\MultiMC\instances\"Fabric 1.18.2"\.minecraft\mods
echo COPYED THE NEW MOD
cd C:\Users\Owner\Documents\MultiMC
echo CD INTO MULTIMC
MultiMC.exe -l "Fabric 1.18.2"
echo LAUNCH THE MOD