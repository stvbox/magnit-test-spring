<?xml version="1.0"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">

    <xsl:output method="xml" indent="yes"/>

    <xsl:template match="entries">
        <entries>
            <xsl:apply-templates/>
        </entries>
    </xsl:template>

    <xsl:template match="entry">
        <entry>
		<xsl:attribute name="field" >
			<xsl:apply-templates select="@*|*" />
		</xsl:attribute>
        </entry>
    </xsl:template>

    <xsl:template match="@username">
        <username>
            <xsl:value-of select="."/>
        </username>
    </xsl:template>

    <xsl:template match="name">
        <fullname>
            <xsl:apply-templates/>
            <xsl:apply-templates select="following-sibling::surname" mode="fullname"/>
        </fullname>
    </xsl:template>

    <xsl:template match="surname"/>

    <xsl:template match="surname" mode="fullname">
        <xsl:text> </xsl:text>
        <xsl:apply-templates/>
    </xsl:template>

</xsl:stylesheet>