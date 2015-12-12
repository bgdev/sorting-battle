#!/usr/bin/env python3
from random import Random
from string import digits, ascii_letters

"""
Writes a 4 GB text file. Each line of the file contains
a random word, which is 64 chars long. The line separator
is '\n'.

To run: python3 sample.py (needs Python-3.x)

Note: Python random is incredibly slow. The Kotlin version is 20x faster.
Author: Fidel Dahan
"""

FILE_SIZE = 4 * 1024 * 1024 * 1024  # 4 GB
ALPHABET64 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789__"
WORD_COUNT = FILE_SIZE // (len(ALPHABET64) + 1)


class Generator(object):
  def __init__(self):
    super().__init__()
    self.buffer = bytearray(source=ALPHABET64, encoding='UTF-8')
    self.line_feed = bytes(source='\n', encoding='UTF-8')
    self.random = Random(x=1)

  def next_word(self):
    self.random.shuffle(self.buffer)
    return self.buffer


def write(generator, sample_file):
  for _ in range(WORD_COUNT):
    sample_file.write(generator.next_word())
    sample_file.write(generator.line_feed)


generator = Generator()
with open("sample.txt", "wb") as sample_file:
  write(generator, sample_file)