package com.mx.skinchange.factory

import android.content.Context
import android.util.AttributeSet
import com.mx.skinchange.SkinManager
import com.mx.skinchange.common.ISkinView
import com.mx.skinchange.common_views.*

object SkinViewRegister {
    /**
     * 内置换肤插件
     */
    private val skinDefaultViewList = HashMap<String, Class<out ISkinView>>()

    /**
     * 外部注册换肤插件
     */
    private val skinViewList = HashMap<String, Class<out ISkinView>>()

    init {
        registerDefault(CommonLinearLayout::class.java)
        registerDefault(CommonRelativeLayout::class.java)
        registerDefault(CommonFrameLayout::class.java)
        registerDefault(CommonTextView::class.java)
        registerDefault(CommonImageView::class.java)
        registerDefault(CommonView::class.java)
        registerDefault(CommonButton::class.java)
        registerDefault(CommonScrollView::class.java)
        registerDefault(CommonHorizontalScrollView::class.java)
        registerDefault(CommonEditText::class.java)
        registerDefault(CommonRadioButton::class.java)
        registerDefault(CommonRadioGroup::class.java)
        registerDefault(CommonCheckBox::class.java)
        registerDefault(CommonCheckTextView::class.java)
        registerDefault(CommonProgressBar::class.java)
        registerDefault(CommonRatingBar::class.java)
        registerDefault(CommonSeekBar::class.java)
    }

    private fun registerDefault(iSkinView: Class<out ISkinView>) {
        val name = getNameByClass(iSkinView) ?: return
        val exitsOne = skinDefaultViewList.keys.firstOrNull { it == name }
        if (exitsOne != null) {
            skinDefaultViewList.remove(exitsOne)
        }
        skinDefaultViewList[name] = iSkinView
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
        return skinViewList[name] ?: skinDefaultViewList[name]
    }
}