<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <HTML>
<HEAD>
<TITLE>客户关系管理系统</TITLE>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">

<script language="JavaScript">
<!--
function getDHTMLObj(docName, objName) {return eval(docName + theBrowser.DHTMLRange + '.' + objName + theBrowser.DHTMLStyleObj);}
function getDHTMLObjTop(theObj) {return (theBrowser.code == "MSIE") ? theObj.pixelTop : theObj.top;}
function getDHTMLObjHeight(docName, objName) {return eval(docName + theBrowser.DHTMLRange + '.' + objName + theBrowser.DHTMLDivHeight);}
function getDHTMLImg(docName, objName, imgName) {
	return (theBrowser.code == 'MSIE') ? eval(docName + '.all.' + imgName) : getDHTMLObj(docName, objName).document.images[imgName];
}
function simpleArray() {this.item = 0;}
function imgStoreItem(n, s, w, h) {
	this.name = n;
	this.src = s;
	this.obj = null;
	this.w = w;
	this.h = h;
	if ((theBrowser.canCache) && (s)) {
		this.obj = new Image(w, h);
		this.obj.src = s;
	}
}
function imgStoreObject() {
	this.count = -1;
	this.img = new imgStoreItem;
	this.find = imgStoreFind;
	this.add = imgStoreAdd;
	this.getSrc = imgStoreGetSrc;
	this.getTag = imgStoreGetTag;
}
function imgStoreFind(theName) {
	var foundItem = -1;
	for (var i = 0; i <= this.count; i++) {if (this.img[i].name == theName) {foundItem = i;break;}}
	return foundItem;
}
function imgStoreAdd(n, s, w, h) {
	var i = this.find(n);
	if (i == -1) {i = ++this.count;}
	this.img[i] = new imgStoreItem(n, s, parseInt(w, 10), parseInt(h, 10));
}
function imgStoreGetSrc(theName) {
	var i = this.find(theName);
	return (i == -1) ? '' : this.img[i].obj.src;
}
function imgStoreGetTag(theName, iconID, altText) {
	var i = this.find(theName);
	if (i < 0) {return ''}
	with (this.img[i]) {
		if (src == '') {return ''}
		var tag = '<img src="' + src +  '" width="' + w + '" height="' + h + '" border="0" align="left" hspace="0" vspace="0"';
		tag += (iconID != '') ? ' name="' + iconID + '"' : '';
		tag += ' alt="' + ((altText)?altText:'') +  '">';
	}
	return tag;
}
// The MenuItem object.  This contains the data and functions for drawing each item.
//这些参数不要随便改动；
function MenuItem (owner, id, type, text, url, status, nItem, pItem, parent) {
	this.owner = owner;
	this.id = id;
	this.type = type;
	this.text = text;
	this.url = url;
	this.status = status;
	this.target = owner.defaultTarget;
	this.nextItem = nItem;
	this.prevItem = pItem;
	this.FirstChild = -1;
	this.parent = parent;
	this.isopen = false;
	this.isSelected = false;
	this.draw = MIDraw;
	this.PMIconName = MIGetPMIconName;
	this.docIconName = MIGetDocIconName;
	this.setImg = MISetImage;
	this.setIsOpen = MISetIsOpen;
	this.setSelected = MISetSelected;
	this.mouseOver = MIMouseOver;
	this.mouseOut = MIMouseOut;
	var i = (this.owner.imgStore) ? this.owner.imgStore.find(type) : -2;
	if (i == -1) {i = this.owner.imgStore.find('iconPlus');}
	this.height = (i > -1) ? this.owner.imgStore.img[i].h : 0;
}
function MIDraw (indentStr) {
	var o = this.owner;
	var mRef = '="return ' + o.reverseRef + "." + o.name;
	var tmp = mRef + '.entry[' + this.id + '].';
	var MOver = ' onMouseOver' + tmp + 'mouseOver(\''
	var MOut = ' onMouseOut' + tmp + 'mouseOut(\''
	var iconTag = o.imgStore.getTag(this.PMIconName(), 'plusMinusIcon' + this.id, '');
	var aLine = '<nobr>' + indentStr;
	if (!this.noOutlineImg) {
		if (this.FirstChild != -1) {
			aLine += '<A HREF="#"'
				+ ' onClick' + mRef + '.toggle(' + this.id + ');"'
				+ MOver + 'plusMinusIcon\',this);"'
				+ MOut + 'plusMinusIcon\');">' + iconTag + '</A>';
		} else {
			aLine += iconTag;
		}
	}
	var tip = eval('this.' + o.tipText);
	var theEntry = o.imgStore.getTag(this.docIconName(), 'docIcon' + this.id, tip) + this.text;
	var theImg = o.imgStore.getTag(this.docIconName(), 'docIcon' + this.id, tip);
	var sTxt = '<SPAN CLASS="' + ((this.CSSClass) ? this.CSSClass : ((this.FirstChild != -1) ? 'node' : 'leaf')) + '">';
	var lTxt = '<A NAME="joustEntry' + this.id + '"';
	var theUrl = (((this.url == '') && theBrowser.canJSVoid && o.showAllAsLinks) || this.noOutlineImg || o.wizardInstalled) ? 'javascript:void(0);' : this.url;
	if (theUrl != '') {
		if (this.target == "_top") {theUrl = "javascript:" + o.reverseRef + ".loadURLInTop('" + theUrl + "');";}
			lTxt += ' HREF="' + theUrl + '"'
			+ ' TARGET="' + this.target + '"'
			+ ' onClick' + mRef + '.itemClicked(' + this.id + ');"'
			+ MOver + 'docIcon\',this);"'
			+ MOut + 'docIcon\');"';
	}
	lTxt += (tip) ? ' TITLE="' + tip + '">' : '>';
	aLine += sTxt + lTxt + theImg;
	if (this.multiLine) {
		aLine += '</A></SPAN><TABLE BORDER="0" CELLPADDING="0" CELLSPACING="0"><TR><TD>' 
			+ sTxt + lTxt + this.text + '</A></SPAN></TD></TR></TABLE>';
	} else {
		aLine += this.text + '</A></SPAN>';
	}
	aLine += '</nobr>';
	return aLine
}
function MIGetPMIconName() {
	var n = 'icon' + ((this.FirstChild != -1) ? ((this.isopen == true) ? 'Minus' : 'Plus') : 'Join');
	n += (this.id == this.owner.firstEntry) ? ((this.nextItem == -1) ? 'Only' : 'Top') : ((this.nextItem == -1) ? 'Bottom' : '');
	return n;
}
function MIGetDocIconName() {
	var is = this.owner.imgStore; var n = this.type;
	n += ((this.isopen) && (is.getSrc(n + 'Expanded') != '')) ? 'Expanded' : '';
	n += ((this.isSelected) && (is.getSrc(n + 'Selected') != '')) ? 'Selected' : '';
	return n;
}
function MISetImage(imgID, imgName) {
	var o = this.owner; var imgSrc = o.imgStore.getSrc(imgName);
	if ((imgSrc != '') && (theBrowser.canCache) && (!o.amBusy)) {
		var img = (theBrowser.hasDHTML) ? getDHTMLImg(o.container + '.document', 'entryDIV' + this.id, imgID) : eval(o.container).document.images[imgID];
		if (img) {img.src = imgSrc;}
	}
}
function MISetIsOpen (isOpen) {
	if ((this.isopen != isOpen) && (this.FirstChild != -1)) {
		this.isopen = isOpen;
		this.setImg('plusMinusIcon' + this.id, this.PMIconName());
		this.setImg('docIcon' + this.id, this.docIconName(false));
		return true;
	} else {
		return false;
	}
}
function MISetSelected (isSelected) {
	this.isSelected = isSelected;
	this.setImg('docIcon' + this.id, this.docIconName(false));
	if ((this.parent >= 0) && this.owner.selectParents) {this.owner.entry[this.parent].setSelected(isSelected);}
}
function MIMouseOver(imgName, theURL) {
	eval(this.owner.container).status = '';  //Needed for setStatus to work on MSIE 3 - Go figure!?
	var newImg = '';
	var s = '';
	if (imgName == 'plusMinusIcon') {
		newImg = this.PMIconName();
		s = '点击' + ((this.isopen == true) ? '關閉.' : '打开.');
	} else {
		if (imgName == 'docIcon') {
			newImg = this.docIconName();
			s = (this.status != null) ? this.status : theURL;
		}
	}
	setStatus(s);
	if (theBrowser.canOnMouseOut) {this.setImg(imgName + this.id, newImg + 'MouseOver');}
	if(this.onMouseOver) {var me=this;eval(me.onMouseOver);}
	return true;
}
function MIMouseOut(imgName) {
	clearStatus();
	var newImg = '';
	if (imgName == 'plusMinusIcon') {
		newImg = this.PMIconName();
	} else {
		if (imgName == 'docIcon') {newImg = this.docIconName();}
	}
	this.setImg(imgName + this.id, newImg);
	if(this.onMouseOut) {var me=this;eval(me.onMouseOut);}
	return true;
}
// The Menu object.  This is basically an array object although the data in it is a tree.
function Menu () {
	this.count = -1;
	this.version = '2.4 beta';
	this.firstEntry = -1;
	this.autoScrolling = false;
	this.modalFolders = false;
	this.linkOnExpand = false;
	this.toggleOnLink = false;
	this.showAllAsLinks = false;
	this.savePage = true;
	this.name = 'theMenu';
	this.container = 'menu';
	this.reverseRef = 'parent';
	this.contentFrame = 'text';
	this.defaultTarget = 'text';
	this.tipText = 'none';
	this.selectParents = false;
	this.lastPMClicked = -1;
	this.selectedEntry = -1;
	this.wizardInstalled = false;
	this.amBusy = true;
	this.maxHeight = 0;
	this.imgStore = new imgStoreObject;
	this.entry = new MenuItem(this, 0, '', '', '', '', -1, -1, -1);
	this.contentWin = MenuGetContentWin;
	this.getEmptyEntry = MenuGetEmptyEntry;
	this.addEntry = MenuAddEntry;
	this.addMenu = MenuAddEntry;
	this.addChild = MenuAddChild;
	this.rmvEntry = MenuRmvEntry;
	this.rmvChildren = MenuRmvChildren;
	this.draw = MenuDraw;
	this.drawALevel = MenuDrawALevel;
	this.refresh = MenuRefresh;
	this.reload = MenuReload;
	this.refreshDHTML = MenuRefreshDHTML;
	this.scrollTo = MenuScrollTo;
	this.itemClicked = MenuItemClicked;
	this.selectEntry = MenuSelectEntry;
	this.setEntry = MenuSetEntry;
	this.setEntryByURL = MenuSetEntryByURL;
	this.setAllChildren = MenuSetAllChildren;
	this.setAll = MenuSetAll;
	this.openAll = MenuOpenAll;
	this.closeAll = MenuCloseAll;
	this.findEntry = MenuFindEntry;
	this.toggle = MenuToggle;
}
function MenuGetContentWin() {
	return eval(((myOpener != null) ? 'myOpener.' : 'self.') + this.contentFrame);
}
function MenuGetEmptyEntry() {
	for (var i = 0; i <= this.count; i++) {if (this.entry[i] == null) {break;}}
	if (i > this.count) {this.count = i};
	return i
}
function MenuAddEntry (addTo, type, text, url, status, insert) {
	if (!insert) {insert=false;}
	var theNI = -1;var theP = -1;var thePI = -1;
	if (addTo < 0) {
		var i = addTo = this.firstEntry;
		if (!insert) {while (i > -1) {addTo = i;i = this.entry[i].nextItem;}}
	}
	if (addTo >= 0) {
		var e = this.entry[addTo];
		if (!e) {return -1;}
		thePI = (insert)?e.prevItem:addTo;
		theNI = (insert)?addTo:e.nextItem;
		theP = e.parent;
	}
	var eNum = this.getEmptyEntry();
	if (thePI >= 0) {
		this.entry[thePI].nextItem = eNum;
	} else {
		if (theP >= 0) {
			this.entry[theP].FirstChild = eNum;
		} else {
			this.firstEntry = eNum;
		}
	}
	if (theNI >= 0) {this.entry[theNI].prevItem = eNum;}
	this.entry[eNum] = new MenuItem(this, eNum, type, text, url, status, theNI, thePI, theP);
	return eNum;
}
function MenuAddChild (addTo, type, text, url, status, insert) {
	if (!insert) {insert=false;}
	var eNum = -1;
	if ((this.count == -1) || (addTo < 0)) {
		eNum = this.addEntry(-1, type, text, url, status, false);
	} else {
		var e = this.entry[addTo];
		if (!e) {return -1;}
		var cID = e.FirstChild;
		if (cID < 0) {
			e.FirstChild = eNum = this.getEmptyEntry();
			this.entry[eNum] = new MenuItem(this, eNum, type, text, url, status, -1, -1, addTo);	
		} else {
			while (!insert && (this.entry[cID].nextItem >= 0)) {cID = this.entry[cID].nextItem;}
			eNum = this.addEntry(cID, type, text, url, status, insert);
		}
	}
	return eNum;
}
function MenuRmvEntry (theEntry) {
	var e = this.entry[theEntry];
	if (e == null) {return;}
	var p = e.prevItem;
	var n = e.nextItem;
	if (e.FirstChild > -1) {this.rmvChildren(theEntry);}
	if (this.firstEntry == theEntry) {this.firstEntry = n}
	if (this.selectedEntry == theEntry) {this.selectedEntry = n}
	if (p > -1) {
		this.entry[p].nextItem = n;
	} else { 
		if (e.parent > -1) {
			this.entry[e.parent].FirstChild = n;
		} else {
			if (this.firstEntry == theEntry) {this.firstEntry = n}
		}
	} 
	if (n > -1) {this.entry[n].prevItem = p;}
	this.entry[theEntry] = null;
}
function MenuRmvChildren (theP) {
	var eNum;var e;var tmp;
	if (theP == -1) {
		eNum = this.firstEntry;
		this.firstEntry = -1;
	} else {
		eNum = this.entry[theP].FirstChild;
		this.entry[theP].FirstChild = -1;
	}
	while (eNum > -1) {
		e = this.entry[eNum];
		if (e.FirstChild > -1) {this.rmvChildren(eNum);}
		if (this.selectedEntry == eNum) {this.selectedEntry = e.parent;}
		tmp = eNum;
		eNum = e.nextItem;
		this.entry[tmp] = null;
	}
}
function MenuDraw() {
	this.maxHeight = 0;
	theDoc = eval(this.container + ".document");
	eval(this.container).document.writeln(this.drawALevel(this.firstEntry, '', true, theDoc));
	if (theBrowser.hasDHTML) {
		for (var i = 0; i <= this.count; i++) {
        	if (this.entry[i]) {
				this.maxHeight += getDHTMLObjHeight(this.container + '.document', 'entryDIV' + i);
			}
		}
	} else {
		if ((this.lastPMClicked > 0) && theBrowser.mustMoveAfterLoad && this.autoScrolling) {
			this.scrollTo(this.lastPMClicked);
		}
	}
}
function MenuDrawALevel(firstItem, indentStr, isVisible, theDoc) {
	var currEntry = firstItem;
	var padImg = "";
	var aLine = "";
	var theLevel = "";
	var e = null;
	while (currEntry > -1) {
		e = this.entry[currEntry];
		aLine = e.draw(indentStr);
		if (theBrowser.hasDHTML) {
			aLine = '<DIV ID="entryDIV' + currEntry + '" CLASS="menuItem">' + aLine + '</DIV>';
		} else {
			aLine += '<BR CLEAR="ALL">';
		}
		theBrowser.lineByLine = true;
		if (theBrowser.lineByLine) {theDoc.writeln(aLine);} else {theLevel += aLine;}
		if ((e.FirstChild > -1) && ((theBrowser.hasDHTML || (e.isopen && isVisible)))) {
			padImg = (e.noOutlineImg) ? '' : this.imgStore.getTag((e.nextItem == -1) ? 'iconBlank' : 'iconLine', '', '');
			theLevel += this.drawALevel(e.FirstChild, indentStr + padImg, (e.isopen && isVisible), theDoc);
		}
		currEntry = e.nextItem;
	}
	return theLevel;
}
function MenuRefresh() {
	if (theBrowser.hasDHTML) {
		if (!this.amBusy) {
			this.refreshDHTML();
			if (this.autoScrolling) {this.scrollTo(this.lastPMClicked);}
		}
	} else {
		this.reload();
	}
}
function MenuReload() {
	if (!this.amBusy) {
		this.amBusy = true;
		var l = eval(this.container).location;
		var rm = theBrowser.reloadMethod;
		var newLoc = fixPath(l.pathname);
		if (theBrowser.code == 'OP') {var d = new Date(); newLoc += '?' + d.getTime();}
		if (this.autoScrolling && (this.lastPMClicked > 0) && !theBrowser.mustMoveAfterLoad) {
			newLoc += "#joustEntry" + this.lastPMClicked;
		}
		if (rm == 'replace') {
			l.replace(newLoc);
		} else {
			if (rm == 'reload') {
				l.reload();
			} else {
				if (rm == 'timeout') {
					setTimeout(this.container + ".location.href ='" + newLoc + "';", 100);
				} else {
					l.href = newLoc;
				}
			}
		}
	}
}
function MenuRefreshDHTML() {
	var nextItemArray = new simpleArray;
	var currEntry = this.firstEntry;
	var level = (currEntry == -1) ? 0 : 1;
	var isVisible = true;
	var lastVisibleLevel = 1;
	var co = eval(this.container);
	var yPos = co.menuStart;
	var d = this.container + '.document';
	var e = null;var s = null;
	while (level > 0) {
		e = this.entry[currEntry];
		s = getDHTMLObj(d, 'entryDIV' + currEntry);
		if (isVisible) {
			s.top = yPos;
			s.visibility = 'visible';
			yPos += getDHTMLObjHeight(d, 'entryDIV' + currEntry);
			lastVisibleLevel = level;
		} else {
			s.visibility = 'hidden';
		}
		if (e.FirstChild > -1) {
			isVisible = (e.isopen == true) && isVisible;
			nextItemArray[level++] = e.nextItem;
			currEntry = e.FirstChild;
		} else {
			if (e.nextItem != -1) {
				currEntry = e.nextItem;
			} else {
				while (level > 0) {
					if (nextItemArray[--level] != -1) {
						currEntry = nextItemArray[level];
						isVisible = (lastVisibleLevel >= level);
						break;
					}
				}
			}
		}
	}
	this.maxHeight = yPos;
	co.setMenuHeight(yPos);
}
function MenuScrollTo(entryNo) {
	if (theBrowser.hasDHTML) {
		var e = this.entry[entryNo];
		if (!e) {return;}
		var co = eval(this.container);
		var d = this.container + '.document';
		var srTop = getDHTMLObjTop(getDHTMLObj(d, 'entryDIV' + entryNo));
		var srBot = (e.nextItem > 0) ? getDHTMLObjTop(getDHTMLObj(d, 'entryDIV' + e.nextItem)) : this.maxHeight;
		if (theBrowser.code == 'MSIE') {
			var curTop = co.document.body.scrollTop;
			var curBot = curTop + co.document.body.clientHeight;
		} else {
			var curTop = co.pageYOffset;
			var curBot = curTop + co.innerHeight;
		}
		if ((srBot > curBot) || (srTop < curTop)) {
			var scrBy = srBot - curBot;
			if (srTop < (curTop + scrBy)) {scrBy = srTop - curTop;}
			co.setTimeout('self.scrollBy(0, ' + scrBy + ');', 100);
		}
	} else {
		var l = eval(this.container).location.pathname + '#joustEntry' + entryNo;
		setTimeout(this.container + '.location.href = "' + l + '";', 100);
	}
}
function MenuItemClicked(entryNo, fromToggle) {
	var r = true;
	var e = this.entry[entryNo];
	var w = this.contentWin();
	var b = theBrowser;
	this.selectEntry(entryNo);
	if (this.wizardInstalled) {w.menuItemClicked(entryNo);}
	if(e.onClickFunc) {e.onClick = e.onClickFunc;}
	if(e.onClick) {var me=e;if(eval(e.onClick) == false) {r = false;}}
	if (r) {
		if (((this.toggleOnLink) && (e.FirstChild != -1) && !(fromToggle)) || e.noOutlineImg) {
			if (b.hasDHTML) {
				this.toggle(entryNo, true);
			} else {
				setTimeout(this.name + '.toggle(' + entryNo + ', true);', 100);
			}
		}
	}
	return (r) ? r : (e.url != '');
}
function MenuSelectEntry(entryNo) {
	var e = this.entry[entryNo];
	if (e) {
		var oe = this.entry[this.selectedEntry];
		if (oe) {oe.setSelected(false);}
		this.selectedEntry = entryNo;
		e.setSelected(true);
	}
}
function MenuSetEntry(entryNo, state) {
	var cl = ',' + entryNo + ',';
	var e = this.entry[entryNo];
	this.lastPMClicked = entryNo;
	var mc = e.setIsOpen(state);
	var p = e.parent;
	while (p >= 0) {
		cl += p + ',';
		e = this.entry[p];
		mc |= (e.setIsOpen(true));
		p = e.parent;
	}
	if (this.modalFolders) {
		for (var i = 0; i <= this.count; i++) {
			e = this.entry[i];
			if ((cl.indexOf(',' + i + ',') < 0) && e) {mc |= e.setIsOpen(false);}
		}
	}
	return mc;
}
function MenuSetEntryByURL(theURL, state) {
	var i = this.findEntry(theURL, 'url', 'right', 0);
	return (i != -1) ? this.setEntry(i, state) : false;
}
function MenuSetAllChildren(state, parentID) {
	var hasChanged = false;
	var currEntry = (parentID > -1) ? this.entry[parentID].FirstChild : this.firstEntry;
	while (currEntry > -1) {
		var e = this.entry[currEntry];
		hasChanged |= e.setIsOpen(state);
		if (e.FirstChild > -1) {hasChanged |= this.setAllChildren(state, currEntry);}
		currEntry = e.nextItem;
	}
	return hasChanged;
}
function MenuSetAll(state, parentID) {
    if (theBrowser.version >= 4) {
		if (parentID == 'undefined') {parentID = -1;}
	} else {
		if (parentID == null) {parentID = -1;}
	}
	var hasChanged = false;
	if (parentID > -1) {hasChanged |= this.entry[parentID].setIsOpen(state);}
	hasChanged |= this.setAllChildren(state, parentID);
	if (hasChanged) {
		this.lastPMClicked = this.firstEntry;
		this.refresh();
	}
}
function MenuOpenAll() {this.setAll(true, -1);}
function MenuCloseAll() {this.setAll(false, -1)}
function MenuFindEntry(srchVal, srchProp, matchType, start) {
	if (srchVal == "") {return -1;}
	if (!srchProp) {srchProp = "url";}
	if (!matchType) {matchType = "exact";}
	if (!start) {start = 0;}
	if (srchProp == "URL") {srchProp = "url";}
	if (srchProp == "title") {srchProp = "text";}
	if (typeof(srchVal) != "string") {matchType = "exact";}
	if ((matchType != "left") && (matchType != "right")) {matchType = "exact";}
	var lm = srchVal.length;
	for (var i = start; i <= this.count; i++) {
		var e = this.entry[i];
		if (e) {
			var currVal = eval("e." + srchProp);
			if (currVal == srchVal) {return i;}
			if ((typeof(currVal) == "string") && (currVal != "")) {
				var lc = currVal.length;
				var l = Math.min(lm,lc);
				if (matchType == "left") {
					if (currVal.substring(1, l) == srchVal.substring(1, l)) {return i;}
				} else {
					if (matchType == "right") {
						if (currVal.substring(lc-l, lc) == srchVal.substring(lm-l, lm)) {return i;}
					}
				}
			}
		}
	}
	return -1;
}
function MenuToggle(entryNo, fromClicked) {
	var r = true;
	var e = this.entry[entryNo];
	if (e.onToggle) {var me=e;if(eval(e.onToggle) == false) {r = false;}}
	if (r) {
		var chg = this.setEntry(entryNo, e.isopen ^ 1);
		if (this.linkOnExpand && e.isopen) {
			if (e.url != '') {this.contentWin().location = e.url;}
			if (!fromClicked) {this.itemClicked(entryNo, true);}
		}
		if (chg) {this.refresh();}
	}
	return false;
}
// Other functions
function DrawMenu(m) {
	m.draw();
}
function browserInfo() {
	this.code = 'unknown';
	this.version = 0;
	this.platform = 'Win';
	var ua = navigator.userAgent;
	var i = ua.indexOf('WebTV');
	if (i >= 0) {
		this.code = 'WebTV';
		i += 6;
	} else {
		i = ua.indexOf('MSIE');
		if (i >= 0) {
			this.code = 'MSIE';
			i += 5;
		} else {
			i = ua.indexOf('Opera');
			if (i >= 0) {
				this.code = 'OP';
				i = ua.indexOf(') ') + 2;
			} else {
				i = ua.indexOf('Mozilla/');
				if (i >= 0) {
					this.code = 'NS';
					i += 8;
				}
			}
		}
	}
	this.version = parseFloat(ua.substring(i, i+4));
	if (ua.indexOf('Mac') >= 0) {this.platform = 'Mac';}
	if (ua.indexOf('OS/2') >= 0) {this.platform = 'OS/2';}
	if (ua.indexOf('X11') >= 0) {this.platform = 'UNIX';}
	var v = this.version;
	var p = this.platform;
	var NS = (this.code == 'NS');
	var IE = (this.code == 'MSIE');
	var WTV = (this.code == 'WebTV');
	var OP = (this.code == 'OP');
	var OP32up = (OP && (v >= 3.2));
	var IE4up = (IE && (v >= 4));
	var NS3up = (NS && (v >= 3));
	this.IE = IE;
	this.NS = NS;
	this.canCache = NS3up || IE4up || OP32up || WTV;
	this.canOnMouseOut = this.canCache;
	this.canOnError = NS3up || IE4up || OP32up;
	this.canJSVoid = !((NS && !NS3up) || (IE && !IE4up) || (OP && (v < 3.5)));
	this.lineByLine = (v < 4);
	this.mustMoveAfterLoad = NS3up || (IE4up && (p != 'Mac')) || WTV;
	if (NS3up || IE4up || WTV) {
		this.reloadMethod = 'replace';
	} else {
		this.reloadMethod =  (NS && (v == 2.01) && (p != 'Win')) ? 'timeout' : 'href';
	}
	this.canFloat = NS || (IE && !((p == 'Mac') && (v >= 4) && (v < 5)));
	this.hasDHTML = ((NS || IE) && (v >= 4)) && !(IE && (p == 'Mac') && (v < 4.5));
	this.needLM = NS || (IE && (p == 'Mac') && (v >= 4.5));
	this.DHTMLRange = IE ? '.all' : '';
    this.DHTMLStyleObj = IE ? '.style' : '';
	this.DHTMLDivHeight = IE ? '.offsetHeight' : '.clip.height';
}

function getWindow() {return (floatingMode) ? myOpener : self;}
//function getWindow() {myOpener : self;}
function setStatus(theText) {
	var theWindow = getWindow();
	if (theWindow) {
		theWindow.status = theText;
		if (!theBrowser.canOnMouseOut) {
			clearTimeout(statusTimeout);
			statusTimeout = setTimeout('clearStatus()', 5000);
		}
	}
	return true;
}
function clearStatus() {
	var theWindow = getWindow();
	if (theWindow) {theWindow.status = '';}
}


function getMode() {
	var theMode = getParm(document.cookie, 'mode', ';');
	return ((theMode == "Floating") || (theMode == "NoFrames")) ? theMode : "Frames";
}
function smOnError (msg, url, lno) {
	smCallerWin.onerror = oldErrorHandler;
	if (confirm(smSecurityMsg)) {setTimeout('setMode("' + smNewMode + '");', 100);}
	return true;
}
function smSetCookie(theMode) {
	document.cookie = 'mode=' + theMode + '; path=/';
	if (getMode() != theMode) {
		alert(smCookieMsg);
		return false;
	} else {
		return true;
	}
}
function setMode(theMode, callerWin) {
	smNewMode = theMode
	smCallerWin = (theBrowser.code == 'NS') ? callerWin : self;
	var okToGo = true;
	var currentMode = getMode();
	if (theMode != currentMode) {
		if (currentMode == 'Floating') {
			if (smSetCookie(theMode)) {self.close();}
		} else {
			var dest = '';
			if (theBrowser.canFloat) {
				if ((theMenu.savePage) && (callerWin)) {
					if (theBrowser.canOnError) {
						oldErrorHandler = smCallerWin.onerror;
						smCallerWin.onerror = smOnError;
					}
					var p = theMenu.contentWin().location.pathname;
					if (theBrowser.canOnError) {smCallerWin.onerror = oldErrorHandler;}
					if (p) {
						dest = fixPath(p);
					} else {
						if (!confirm(smSecurityMsg)) {okToGo = false;}
					}
				}
			} else {
				alert(smNoFloat);
				okToGo = false;
			}
			if (okToGo && smSetCookie(theMode)) {
				if (theMode == 'NoFrames') {
					location.href = (index3 == '') ? ((dest == '') ? '/' : dest) : index3;
				} else {
					location.href = index2 + '?page=' + escape(dest);
				}
			}
		}
	}
}
function fixPath(p) {
	return (p.substring(0,2) == '/:') ? p.substring(p.indexOf('/', 2), p.length) : p;
}
function getParm(theStr, parm, delim) {
     // returns value of parm from string
     if (theStr.length == 0) {return '';}
	 var sPos = theStr.indexOf(parm + "=");
     if (sPos == -1) {return '';}
     sPos = sPos + parm.length + 1;
     var ePos = theStr.indexOf(delim, sPos);
     if (ePos == -1) {ePos = theStr.length;}
     return unescape(theStr.substring(sPos, ePos));
}
function pageFromSearch(def, m, selIt) {
	var s = self.location.search;
	if ((s == null) || (s.length <= 1)) {return def;}
	var p = getParm(s, 'page', '&');
	p = (p != '') ? fixPath(p) : fixPath(s.substring(1, s.length));
	if (m != null) {
		var e = m.findEntry(p, 'URL', 'exact');
		if ((e != -1) && selIt) {
			m.setEntry(e, true);
			m.selectEntry(e);
		}
	}
	return p;
}
function loadURLInTop(theURL) {
	var theWindow = getWindow();
	if (theWindow) {theWindow.top.location.href = theURL;}
}
function defOnError(msg, url, lno) {
	if (jsErrorMsg == '') {
		return false;
	} else {
		alert(jsErrorMsg + '.\n\nError: ' + msg + '\nPage: ' + url + '\nLine: ' + lno + '\nBrowser: ' + navigator.userAgent);
		return true;
	}
}

// Declare global variables
var theBrowser = new browserInfo;

var jsErrorMsg = '頁面发生脚本错误';
jsErrorMsg += '請通知管理員.';
if (theBrowser.canOnError) {self.onerror = defOnError;}

var theMenu = new Menu;
var JoustFrameset = true;
var statusTimeout = 0;
var index1 = 'index.html';
//var index2 = 'index2.htm';
//var index3 = 'index3.htm';
var smCallerWin;
var smNewMode;
var oldErrorHandler;
var smNoFloat = 'Sorry, your browser does not support this feature!';
var smCookieMsg = 'You must have Cookies enabled to change the display mode!';
var smSecurityMsg = 'Due to security restrictions imposed by your browser, I cannot ';
smSecurityMsg += 'change modes while a page from another server is being displayed. ';
smSecurityMsg += 'The default home page for this site will be displayed instead.';



var floatingMode = (getMode() == 'Floating');
var myOpener = null;
if (floatingMode == true) {
	if (self.opener) {
		myOpener = self.opener;
		if (myOpener.JoustFrameset) {myOpener.setTimeout('setGlobals();', 100);}
	} else {
		document.cookie = 'mode=Frames; path=/';
		floatingMode = false;
	}
} else {
	if (getMode() != 'Frames') {document.cookie = 'mode=Frames; path=/';}
}


//	############   End ############

function initOutlineIcons(imgStore) {
	var ip = 'images/menu/';
	ip += (theBrowser.platform == 'Mac') ? 'mac/' : ((theBrowser.platform == 'OS/2') ? 'os2/' : 'win/');
	
	imgStore.add('iconPlusTop', ip + 'plustop.gif', 18, 16);
	imgStore.add('iconPlus', ip + 'plus.gif', 18, 16);
	imgStore.add('iconPlusBottom', ip + 'plusbottom.gif', 18, 16);
	imgStore.add('iconPlusOnly', ip + 'plusonly.gif', 18, 16);
	imgStore.add('iconMinusTop', ip + 'minustop.gif', 18, 16);
	imgStore.add('iconMinus', ip + 'minus.gif', 18, 16);
	imgStore.add('iconMinusBottom', ip + 'minusbottom.gif', 18, 16);
	imgStore.add('iconMinusOnly', ip + 'minusonly.gif', 18, 16);
	imgStore.add('iconLine', ip + 'line.gif', 18, 16);
	imgStore.add('iconBlank', ip + 'blank.gif', 18, 16);
	imgStore.add('iconJoinTop', ip + 'jointop.gif', 18, 16);
	imgStore.add('iconJoin', ip + 'join.gif', 18, 16);
	imgStore.add('iconJoinBottom', ip + 'joinbottom.gif', 18, 16);

	//Add folder and document images to the imgStore.
	imgStore.add('Folder', ip + 'folderclosed.gif', 18, 16);
	
	var di = 'images/menu/';
	if ((theBrowser.code == 'NS') || (theBrowser.code == 'MSIE')) {
		di += theBrowser.code.toLowerCase() + '_doc';
		imgStore.add('Document', di + '.gif', 18, 16);
		imgStore.add('DocumentMouseOver', di + '_mo.gif', 18, 16);
		imgStore.add('DocumentSelected', di + '_sel.gif', 18, 16);
	} else {
		imgStore.add('Document', di + 'doc.gif', 18, 16);
	}

}

//-->
</script>

<SCRIPT LANGUAGE="JavaScript">
<!--

function initialise() {
	// Tell where to find the various index files it needs
	index1 = 'index-ec.html';
	//index2 = 'index2.htm';
	//index3 = 'index3.htm';

	// Set up parameters to control menu behaviour
	theMenu.autoScrolling = true;	
	theMenu.modalFolders = false;
	theMenu.linkOnExpand = false;
	theMenu.toggleOnLink = false;
	theMenu.showAllAsLinks = false;
	theMenu.savePage = true;
	theMenu.tipText = "status";
	theMenu.selectParents = false;
	theMenu.name = "theMenu";
	theMenu.container = "self.menu";
	theMenu.reverseRef = "parent";
	theMenu.contentFrame = "text";
	theMenu.defaultTarget = "text";

	// Initialise all the icons
	initOutlineIcons(theMenu.imgStore);
	
	// Now set up the menu with a whole lot of addEntry and addChild function calls
	var level1ID = -1;
	var leveROOT_MENU = -1;
	var level3ID = -1;
	var level4ID = -1;
	var level5ID = -1;
	//level表示最高级，level2子级，level3子子级，Document文件，folder文件夹。
	//Set the initial state of the folder to "open"
	ROOT_MENU = theMenu.addEntry(-1, "Folder", "客户关系管理系统", "mywork.htm", "客户关系管理系统");
	
	${menu}
	
	theMenu.entry[ROOT_MENU].isopen = true;
}

self.defaultStatus = "";	

//-->
</script>

</HEAD>
<SCRIPT LANGUAGE="JavaScript">
<!--
if (self.name == 'menu') {
	
	self.location.href = "menu2.html";
} else {
	initialise();
	var thePage = pageFromSearch('Welcome.html', theMenu, true);
	
//下面用js脚本写FRAMESET结构；

if (theBrowser.IE){
	self.document.writeln('<frameset rows="*" cols="160,*" border="0"  noresize framespacing="2">'); 
	self.document.writeln('<frame name="menu" src="menu.html"  border="0" scrolling="auto" marginwidth="0"  leftmargin="0" marginheight="0" APPLICATION="yes">');
	self.document.writeln('<frame name="text" src="mywork.htm"  frameborder="no"  marginwidth="0" marginheight="0" APPLICATION="yes">');
	self.document.writeln('</frameset>');
}else{
	self.document.writeln('<frameset rows="*" cols="160,*" border="0"  noresize framespacing="2">'); 
		self.document.writeln('<frame name="left" frameborder="no"  scrolling="no"  noresize marginwidth="0"  marginheight="0" >');
		self.document.writeln('<frame name="menu" src="menu.html"  border="0" scrolling="auto" marginwidth="0"  leftmargin="0" marginheight="0" APPLICATION="yes">');
	self.document.writeln('<frame name="text" src="desk.htm"  frameborder="no"  marginwidth="0" marginheight="0" APPLICATION="yes">');
	self.document.writeln('</frameset>');
}

}

//-->
</SCRIPT>
<NOSCRIPT>
<BODY BGCOLOR="#FFFFCC">
<h1>树形菜单</h1>
你的浏览器不支持JavaScript.
</BODY>
</NOSCRIPT>

</HTML>
    