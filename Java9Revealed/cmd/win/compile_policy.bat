FOR /F "tokens=1 delims=" %%A in ('dir com.jdojo.policy\src\*.java /S /B') do javac -d com.jdojo.policy\build\classes %%A
