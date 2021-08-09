package com.mx.skinchange.factory

import android.content.Context
import android.util.AttributeSet
import com.mx.skinchange.SkinManager
import com.mx.skinchange.common_views.*
import java.lang.Exception

object SkinViewRegister {
    private val skinViewList = HashMap<String, Class<out ISkinView>>()

    init {
        register(CommonLinearLayout::class.java)
        register(CommonRelativeLayout::class.java)
        register(CommonFrameLayout::class.java)
        register(CommonTextView::class.java)
        register(CommonImageView::class.java)
    }

    fun register(iSkinView: Class<out ISkinView>) {
        val name = getNameByClass(iSkinView) ?: return
        val exitsOne = skinViewList.keys.firstOrNull { it == name }
        if (exitsOne != null) {
            skinViewList.remove(exitsOne)
        }
        skinViewList[name] = iSkinView
    }

    private fun getNameByClass(iSkinView: Class<out ISkinView>): String? {
        return createSkinView(iSkinView, SkinManager.appContext, null)?.getName()
    }

    fun createSkinView(
        clazz: Class<out ISkinView>,
        context: Context?,
        attributeSet: AttributeSet?
    ): ISkinView? {
        try {
            val constructor =
                clazz.getConstructor(Context::class.java, AttributeSet::class.java, Int::class.java)
            return constructor.newInstance(context, attributeSet, 0)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    fun getRegisterClass(name: String): Class<out ISkinView>? {
        return skinViewList[name]
    }
}