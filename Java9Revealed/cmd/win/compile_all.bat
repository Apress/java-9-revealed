FOR /F "tokens=1 delims=" %%A in ('dir src\*.java /S /B') do javac -d build\classes %%A
