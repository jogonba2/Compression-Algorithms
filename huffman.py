#!/usr/bin/env python
# -*- coding: utf-8 -*-
#
#  huffman.py
#  
#  Copyright 2015 Overxflow13

import heapq

class Node:
	
	def __init__(self,symbol,prob,lt,rt): self.symbol,self.prob,self.lt,self.rt = symbol,prob,lt,rt
	
	def getSymbol(self): return self.symbol
	def getProbability(self): return self.prob
	def getLeftSon(self): return self.lt
	def getRightSon(self): return self.rt
	
	def setSymbol(self,symbol): self.symbol = symbol
	def setProbability(self,probability): self.probability = probability
	def setLeftSon(self,lt): self.lt = lt
	def setRightSon(selfrt): self.rt = rt

	
def getCharCount(data):
	h = {}
	for i in data: h[i] = h[i]+1 if i in h else 1
	return h

def huffman(data):
	h,i,r,l = getCharCount(data),0,{},[]
	n     = len(h.keys())
	# HeapSort #
	for key in h: heapq.heappush(l,(h[key],Node(key,h[key],None,None)))
	l = [heapq.heappop(l) for i in xrange(len(l))]
	############
	while len(l)>1:
		m1,m2    = heapq.heappop(l)[1],heapq.heappop(l)[1]
		np,nn    = m1.getProbability()+m2.getProbability(),m1.getSymbol()+m2.getSymbol()
		n = Node(nn,np,m1,m2)
		heapq.heappush(l,(np,n))
	root = l[0][1]
	hc,r    = getHuffmanCode(root,"",[]),{}
	for t in hc: r[t[1]] = t[0]
	return (root,r)

def encoding(data,huffman):
	encoded = ""
	for symbol in data: encoded += huffman[symbol]
	return encoded

def decoding(data,root):
	decoded,actNode = "",root
	while data:
		if actNode.getLeftSon():
			if   data[0]=="0": actNode = actNode.getLeftSon()
			elif data[0]=="1": actNode = actNode.getRightSon()
			data = data[1:]		
		else: decoded,actNode = decoded+actNode.getSymbol(),root
	return decoded
	
def getHuffmanCode(actNode,coding,l):
	if actNode.getLeftSon():
		codel,coder = getHuffmanCode(actNode.getLeftSon(),coding+"0",l),getHuffmanCode(actNode.getRightSon(),coding+"1",l)
		if type(codel)==tuple: l.append(codel)
		if type(coder)==tuple: l.append(coder)
		return l
	else: return (coding,actNode.getSymbol())

if __name__ == "__main__":
	text = raw_input("Text > ")
	hc = huffman(text)
	root,codes = hc[0],hc[1]
	encoded = encoding(text,codes)
	print "CodeTable:",codes
	print "Encoding:",encoded
	print "Decoding:",decoding(encoded,root)
	print "Compression rate:",str(len(text)*8)+"/"+str(len(encoded))+"="+str(float((len(text)*8))/len(encoded))+"x"
