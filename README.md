
# SimProb-Engine

### Introduction
The core logic of SimProb-Engine was born from an early experiment in 8th grade, fueled by a curiosity to create a logic to predict the next word to further scale it to sentences and paragraphs.

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


### Known Limitations & Technical Debt

As a deterministic engine built from scratch, **SimProb-Engine (v1.0)** has specific architectural constraints that provide opportunities for future optimization:

#### 1. Positional Rigidity (The "Index" Flaw)
The current similarity algorithm relies on a rigid character-by-character comparison: `s1.charAt(i) == s2.charAt(i)`.
* **The Problem:** It lacks **Shift-Invariance**. A single leading space or a prefix in the input can result in a similarity score of zero, even if the words are identical.
* **Impact:** The engine is highly sensitive to exact formatting and cannot handle minor input variations.

#### 2. Semantic Blindness
The engine operates on **Morphological Similarity** (how words are spelled) rather than **Semantic Similarity** (what words mean).
* **The Problem:** It has no concept of synonyms. To the engine, "angry" is mathematically closer to "angle" than to "furious."
* **Impact:** Outputs may be structurally similar to the training data but can lack logical coherence or "meaning."

#### 3. Contextual Amnesia (Markov Constraint)
The engine currently predicts the "next word" by looking at a single best-fit sentence rather than a weighted probability of word-to-word transitions.
* **The Problem:** It lacks an **N-Gram Frequency Map** or Markov Chain logic.
* **Impact:** This can lead to "looping," where the engine gets stuck in a repetitive cycle if it finds a sentence with a high enough similarity score to the current string.

#### 4. Heuristic Scalability (The 'Expert System' Bottleneck)
The **Beautifier** is an "Expert System" that relies on hard-coded rules.
* **The Problem:** English grammar is too vast for manual mapping. As more rules are added, the risk of "Logic Collisions" (where one rule breaks another) increases.
* **Impact:** The engine requires manual intervention for every new irregular verb or grammatical edge case discovered.

