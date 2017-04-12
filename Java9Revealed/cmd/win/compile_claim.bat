FOR /F "tokens=1 delims=" %%A in ('dir com.jdojo.claim\src\*.java /S /B') do javac --module-path com.jdojo.policy\build\classes -d com.jdojo.claim\build\classes %%A
