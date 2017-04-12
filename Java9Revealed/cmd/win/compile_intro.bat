FOR /F "tokens=1 delims=" %%A in ('dir com.jdojo.intro\src\*.java /S /B') do javac --module-path com.jdojo.intro\build\classes -d com.jdojo.intro\build\classes %%A
