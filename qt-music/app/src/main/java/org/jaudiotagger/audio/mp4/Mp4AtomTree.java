package org.jaudiotagger.audio.mp4;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Logger;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.NullBoxIdException;
import org.jaudiotagger.audio.mp4.atom.Mp4BoxHeader;
import org.jaudiotagger.audio.mp4.atom.Mp4MetaBox;
import org.jaudiotagger.audio.mp4.atom.Mp4StcoBox;
import org.jaudiotagger.audio.mp4.atom.NullPadding;
import org.jaudiotagger.utils.tree.DefaultMutableTreeNode;
import org.jaudiotagger.utils.tree.DefaultTreeModel;

/* loaded from: classes3.dex */
public class Mp4AtomTree {
    public static Logger logger = Logger.getLogger("org.jaudiotagger.audio.mp4");
    private DefaultTreeModel dataTree;
    private DefaultMutableTreeNode hdlrWithinMdiaNode;
    private DefaultMutableTreeNode hdlrWithinMetaNode;
    private DefaultMutableTreeNode ilstNode;
    private DefaultMutableTreeNode mdatNode;
    private DefaultMutableTreeNode metaNode;
    private ByteBuffer moovBuffer;
    private Mp4BoxHeader moovHeader;
    private DefaultMutableTreeNode moovNode;
    private DefaultMutableTreeNode rootNode;
    private DefaultMutableTreeNode tagsNode;
    private DefaultMutableTreeNode udtaNode;
    private List<DefaultMutableTreeNode> stcoNodes = new ArrayList();
    private List<DefaultMutableTreeNode> freeNodes = new ArrayList();
    private List<DefaultMutableTreeNode> mdatNodes = new ArrayList();
    private List<DefaultMutableTreeNode> trakNodes = new ArrayList();
    private List<Mp4StcoBox> stcos = new ArrayList();

    public Mp4AtomTree(SeekableByteChannel seekableByteChannel) throws CannotReadException, IOException {
        buildTree(seekableByteChannel, true);
    }

    public Mp4AtomTree(File file) throws CannotReadException, IOException {
        buildTree(Files.newByteChannel(file.toPath(), StandardOpenOption.READ, StandardOpenOption.WRITE), true);
    }

    public Mp4AtomTree(File file, boolean z) throws CannotReadException, IOException {
        buildTree(Files.newByteChannel(file.toPath(), StandardOpenOption.READ, StandardOpenOption.WRITE), z);
    }

    public Mp4AtomTree(SeekableByteChannel seekableByteChannel, boolean z) throws CannotReadException, IOException {
        buildTree(seekableByteChannel, z);
    }

    /* JADX WARN: Code restructure failed: missing block: B:68:0x01a9, code lost:
    
        r2 = r14.size() - r14.position();
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x01b4, code lost:
    
        if (r2 == 0) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x01b6, code lost:
    
        org.jaudiotagger.audio.mp4.Mp4AtomTree.logger.warning(org.jaudiotagger.logging.ErrorMessage.EXTRA_DATA_AT_END_OF_MP4.getMsg(java.lang.Long.valueOf(r2)));
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x01c9, code lost:
    
        r0 = r13.dataTree;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x01cd, code lost:
    
        if (r13.mdatNode == null) goto L77;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x01cf, code lost:
    
        if (r15 == false) goto L76;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x01d1, code lost:
    
        r14.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x01d4, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x01e0, code lost:
    
        throw new org.jaudiotagger.audio.exceptions.CannotReadException(org.jaudiotagger.logging.ErrorMessage.MP4_CANNOT_FIND_AUDIO.getMsg());
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public org.jaudiotagger.utils.tree.DefaultTreeModel buildTree(java.nio.channels.SeekableByteChannel r14, boolean r15) throws org.jaudiotagger.audio.exceptions.CannotReadException, java.io.IOException {
        /*
            Method dump skipped, instructions count: 504
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jaudiotagger.audio.mp4.Mp4AtomTree.buildTree(java.nio.channels.SeekableByteChannel, boolean):org.jaudiotagger.utils.tree.DefaultTreeModel");
    }

    public void printAtomTree() {
        Enumeration enumerationPreorderEnumeration = this.rootNode.preorderEnumeration();
        while (enumerationPreorderEnumeration.hasMoreElements()) {
            DefaultMutableTreeNode defaultMutableTreeNode = (DefaultMutableTreeNode) enumerationPreorderEnumeration.nextElement();
            Mp4BoxHeader mp4BoxHeader = (Mp4BoxHeader) defaultMutableTreeNode.getUserObject();
            if (mp4BoxHeader != null) {
                String str = "";
                for (int i = 1; i < defaultMutableTreeNode.getLevel(); i++) {
                    str = str + "\t";
                }
                if (mp4BoxHeader instanceof NullPadding) {
                    if (mp4BoxHeader.getLength() == 1) {
                        System.out.println(str + "Null pad  @ " + mp4BoxHeader.getFilePos() + " 64bitDataSize ,ends @ " + (mp4BoxHeader.getFilePos() + mp4BoxHeader.getLength()));
                    } else {
                        System.out.println(str + "Null pad  @ " + mp4BoxHeader.getFilePos() + " of size:" + mp4BoxHeader.getLength() + " ,ends @ " + (mp4BoxHeader.getFilePos() + mp4BoxHeader.getLength()));
                    }
                } else if (mp4BoxHeader.getLength() == 1) {
                    System.out.println(str + "Atom " + mp4BoxHeader.getId() + " @ " + mp4BoxHeader.getFilePos() + " 64BitDataSize ,ends @ " + (mp4BoxHeader.getFilePos() + mp4BoxHeader.getLength()));
                } else {
                    System.out.println(str + "Atom " + mp4BoxHeader.getId() + " @ " + mp4BoxHeader.getFilePos() + " of size:" + mp4BoxHeader.getLength() + " ,ends @ " + (mp4BoxHeader.getFilePos() + mp4BoxHeader.getLength()));
                }
            }
        }
    }

    public void buildChildrenOfNode(ByteBuffer byteBuffer, DefaultMutableTreeNode defaultMutableTreeNode) throws CannotReadException, IOException {
        Mp4BoxHeader mp4BoxHeader;
        Mp4BoxHeader mp4BoxHeader2 = (Mp4BoxHeader) defaultMutableTreeNode.getUserObject();
        int iPosition = byteBuffer.position();
        if (mp4BoxHeader2.getId().equals(Mp4AtomIdentifier.META.getFieldName())) {
            new Mp4MetaBox(mp4BoxHeader2, byteBuffer).processData();
            try {
                try {
                    new Mp4BoxHeader(byteBuffer);
                } catch (NullBoxIdException unused) {
                    byteBuffer.position(byteBuffer.position() - 4);
                }
            } finally {
                byteBuffer.position(byteBuffer.position() - 8);
            }
        }
        int iPosition2 = byteBuffer.position();
        while (byteBuffer.position() < (mp4BoxHeader2.getDataLength() + iPosition2) - 8) {
            Mp4BoxHeader mp4BoxHeader3 = new Mp4BoxHeader(byteBuffer);
            mp4BoxHeader3.setFilePos(this.moovHeader.getFilePos() + byteBuffer.position());
            logger.finest("Atom " + mp4BoxHeader3.getId() + " @ " + mp4BoxHeader3.getFilePos() + " of size:" + mp4BoxHeader3.getLength() + " ,ends @ " + (mp4BoxHeader3.getFilePos() + mp4BoxHeader3.getLength()));
            DefaultMutableTreeNode defaultMutableTreeNode2 = new DefaultMutableTreeNode(mp4BoxHeader3);
            defaultMutableTreeNode.add(defaultMutableTreeNode2);
            if (mp4BoxHeader3.getId().equals(Mp4AtomIdentifier.UDTA.getFieldName())) {
                this.udtaNode = defaultMutableTreeNode2;
            } else if (mp4BoxHeader3.getId().equals(Mp4AtomIdentifier.META.getFieldName()) && mp4BoxHeader2.getId().equals(Mp4AtomIdentifier.UDTA.getFieldName())) {
                this.metaNode = defaultMutableTreeNode2;
            } else if (mp4BoxHeader3.getId().equals(Mp4AtomIdentifier.HDLR.getFieldName()) && mp4BoxHeader2.getId().equals(Mp4AtomIdentifier.META.getFieldName())) {
                this.hdlrWithinMetaNode = defaultMutableTreeNode2;
            } else if (mp4BoxHeader3.getId().equals(Mp4AtomIdentifier.HDLR.getFieldName())) {
                this.hdlrWithinMdiaNode = defaultMutableTreeNode2;
            } else if (mp4BoxHeader3.getId().equals(Mp4AtomIdentifier.TAGS.getFieldName())) {
                this.tagsNode = defaultMutableTreeNode2;
            } else if (mp4BoxHeader3.getId().equals(Mp4AtomIdentifier.STCO.getFieldName())) {
                this.stcos.add(new Mp4StcoBox(mp4BoxHeader3, byteBuffer));
                this.stcoNodes.add(defaultMutableTreeNode2);
            } else if (mp4BoxHeader3.getId().equals(Mp4AtomIdentifier.ILST.getFieldName())) {
                DefaultMutableTreeNode defaultMutableTreeNode3 = (DefaultMutableTreeNode) defaultMutableTreeNode.getParent();
                if (defaultMutableTreeNode3 != null && (mp4BoxHeader = (Mp4BoxHeader) defaultMutableTreeNode3.getUserObject()) != null && mp4BoxHeader2.getId().equals(Mp4AtomIdentifier.META.getFieldName()) && mp4BoxHeader.getId().equals(Mp4AtomIdentifier.UDTA.getFieldName())) {
                    this.ilstNode = defaultMutableTreeNode2;
                }
            } else if (mp4BoxHeader3.getId().equals(Mp4AtomIdentifier.FREE.getFieldName())) {
                this.freeNodes.add(defaultMutableTreeNode2);
            } else if (mp4BoxHeader3.getId().equals(Mp4AtomIdentifier.TRAK.getFieldName())) {
                this.trakNodes.add(defaultMutableTreeNode2);
            }
            if (mp4BoxHeader3.getId().equals(Mp4AtomIdentifier.TRAK.getFieldName()) || mp4BoxHeader3.getId().equals(Mp4AtomIdentifier.MDIA.getFieldName()) || mp4BoxHeader3.getId().equals(Mp4AtomIdentifier.MINF.getFieldName()) || mp4BoxHeader3.getId().equals(Mp4AtomIdentifier.STBL.getFieldName()) || mp4BoxHeader3.getId().equals(Mp4AtomIdentifier.UDTA.getFieldName()) || mp4BoxHeader3.getId().equals(Mp4AtomIdentifier.META.getFieldName()) || mp4BoxHeader3.getId().equals(Mp4AtomIdentifier.ILST.getFieldName())) {
                buildChildrenOfNode(byteBuffer, defaultMutableTreeNode2);
            }
            byteBuffer.position(byteBuffer.position() + mp4BoxHeader3.getDataLength());
        }
        byteBuffer.position(iPosition);
    }

    public DefaultTreeModel getDataTree() {
        return this.dataTree;
    }

    public DefaultMutableTreeNode getMoovNode() {
        return this.moovNode;
    }

    public List<DefaultMutableTreeNode> getStcoNodes() {
        return this.stcoNodes;
    }

    public DefaultMutableTreeNode getIlstNode() {
        return this.ilstNode;
    }

    public Mp4BoxHeader getBoxHeader(DefaultMutableTreeNode defaultMutableTreeNode) {
        if (defaultMutableTreeNode == null) {
            return null;
        }
        return (Mp4BoxHeader) defaultMutableTreeNode.getUserObject();
    }

    public DefaultMutableTreeNode getMdatNode() {
        return this.mdatNode;
    }

    public DefaultMutableTreeNode getUdtaNode() {
        return this.udtaNode;
    }

    public DefaultMutableTreeNode getMetaNode() {
        return this.metaNode;
    }

    public DefaultMutableTreeNode getHdlrWithinMetaNode() {
        return this.hdlrWithinMetaNode;
    }

    public DefaultMutableTreeNode getHdlrWithinMdiaNode() {
        return this.hdlrWithinMdiaNode;
    }

    public DefaultMutableTreeNode getTagsNode() {
        return this.tagsNode;
    }

    public List<DefaultMutableTreeNode> getFreeNodes() {
        return this.freeNodes;
    }

    public List<DefaultMutableTreeNode> getTrakNodes() {
        return this.trakNodes;
    }

    public List<Mp4StcoBox> getStcos() {
        return this.stcos;
    }

    public ByteBuffer getMoovBuffer() {
        return this.moovBuffer;
    }

    public Mp4BoxHeader getMoovHeader() {
        return this.moovHeader;
    }
}
