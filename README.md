# SimProb-Engine

A lightweight, deterministic Natural Language Processing (NLP) engine built entirely from scratch in Java. **SimProb-Engine** explores the intersection of positional string similarity and rule-based grammatical heuristics to generate text from a philosophical training corpus.

## 🚀 The Concept
Unlike modern LLMs that rely on massive neural networks and external APIs, SimProb-Engine operates on pure logic. It treats text generation as a mathematical search problem combined with a hard-coded linguistic "Beautifier" to maintain syntactic integrity.

## 🧠 How It Works

### 1. Data Ingestion
The engine utilizes a `BufferedReader` to stream training data from external `.txt` files into an internal corpus. This separation of concerns allows the engine to be retrained on any text-based dataset.

### 2. Positional Similarity Algorithm
The core "brain" uses a custom similarity scoring function:
$$p = \frac{e}{L}$$
Where:
*   **e**: Number of exact character matches at identical indices.
*   **L**: Length of the target string.

This creates a deterministic path for word prediction based on the structure of the training data.

### 3. The 'Beautifier' (Heuristic Layer)
The most complex part of the engine is the grammatical correction layer. It functions as a manual "post-processor" to ensure the output adheres to English syntax rules:
*   **Irregular Verb Mapping**: Uses a high-performance `HashMap` lookup for $O(1)$ conjugation (e.g., mapping "fly" → "flown", "think" → "thought").
*   **Subject-Verb Agreement**: Dynamically corrects helping verbs based on the subject (e.g., forcing "I am" instead of "I is").
*   **Tense Enforcement**: Automatically manages `-ing` and `-ed` suffixes based on the presence of specific auxiliary verbs.

## 🛠️ Tech Stack
*   **Language:** Java (OpenJDK)
*   **I/O:** Classic `java.io` (BufferedReader, File) for efficient stream handling.
*   **Logic:** Deterministic State Logic & Heuristic Mapping.

## 📦 Installation & Usage

1. Clone the repository:
   ```bash
   git clone [https://github.com/Bhaveshsingh-Pawar/SimProb-Engine.git](https://github.com/Bhaveshsingh-Pawar/SimProb-Engine.git)
