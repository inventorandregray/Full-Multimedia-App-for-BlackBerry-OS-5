# BlackBerry OS 5 Multimedia Suite

A fully functional **multimedia suite for BlackBerry OS 5** written in **Java ME**, fully native and optimized for limited device resources.  
Includes **audio playlist**, **image slideshow**, **video playlist**, and basic **UI controls**.

---

## 🎵 Features Implemented

### 1. Image Slideshow
- **Next / Previous** buttons.  
- Loads images from project resources (`.png` or `.bmp`).  

### 2. Audio Playlist
- Play **current audio file**.  
- Move to **next audio track**.  

### 3. Video Playlist
- Play **current video file** (`.3gp` preferred).  
- Move to **next video track**.  

### 4. UI Controls
- Fully native using **ButtonField** and **BitmapField**.  
- **Error handling** via `Dialog.alert()`.  

### 5. Resource Management
- Stops and closes **media players** to avoid memory leaks.  
- Keeps **UI responsive**.  

---

## ⚙️ Notes on OS 5 Compatibility
- Video must be **small (<100 KB)** for smooth playback.  
- Audio files should be **short (<2–3 MB)** to prevent memory issues.  
- No background multitasking for media; playback happens in the **same thread**.  
- Minimal touch support on OS 5 devices; buttons mainly work with **trackball/keyboard**.  

---

## 📜 License
**MIT License**  
© Andre Gray 2009  

---

## 🔗 Links
- Author: [Andre Gray](https://github.com/inventorandregray)  
