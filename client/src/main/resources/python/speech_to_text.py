import speech_recognition as sr
import sys
import signal

import warnings
warnings.filterwarnings("ignore")

def signal_handler(sig, frame):
    #print('Recording stopped')
    sys.exit(0)

signal.signal(signal.SIGINT, signal_handler)

def record_speech():
    recognizer = sr.Recognizer()
    with sr.Microphone() as source:
        #print("Listening... Press Ctrl+C to stop.")
        audio_data = recognizer.listen(source, timeout=1, phrase_time_limit=3)
        #print("Done listing...")
        try:
            text = recognizer.recognize_google(audio_data)
            return text
        except sr.UnknownValueError:
            return "Google Speech Recognition could not understand audio"
        except sr.RequestError as e:
            return f"Could not request results; {e}"
        except Exception as e:
            return str(e)

if __name__ == "__main__":
    result = record_speech()
    print(result)
