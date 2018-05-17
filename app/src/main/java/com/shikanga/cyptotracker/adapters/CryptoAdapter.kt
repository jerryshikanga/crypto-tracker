package com.shikanga.cyptotracker.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shikanga.cyptotracker.R
import com.shikanga.cyptotracker.models.CryptoModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.crypto_layout.view.*
import com.shikanga.cyptotracker.utils.Constants
import com.shikanga.cyptotracker.utils.inflate
import java.util.*

/**
 * Created by jerry on 5/18/18.
 */
class CryptoAdapter : RecyclerView.Adapter<CryptoAdapter.CryptoViewHolder>() {
    private var cryptoCoins: List<CryptoModel> = Collections.emptyList()

    /**
     *  lets the Adapter know how many items to display
     */
    override fun getItemCount(): Int = cryptoCoins.count()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoViewHolder = CryptoViewHolder(parent.inflate(R.layout.crypto_layout))

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: CryptoViewHolder, position: Int) {
        /**
         *  coin - A single CryptoModel object from list
         */
        val coin = cryptoCoins.get(position)

        /**
         * get CryptoModel data and bind them to corresponding
         * viewholder widgets(text view, imageview etc)
         */
        holder.apply {
            coinName.text = coin.name
            coinSymbol.text = coin.symbol
            coinPrice.text = coin.price_usd
            oneHourChange.text = coin.percent_change_1h + "%"
            twentyFourHourChange.text = coin.percent_change_24h + "%"
            sevenDayChange.text = coin.percent_change_7d + "%"

            /**
             *  Picasso for async image loading
             */

            Picasso.get().load(Constants.imageUrl + coin.symbol.toLowerCase() + ".png").into(coinIcon)

            /**
             *  Set color of percentage change textview to reflect
             *  if the percentage change was negative or positive
             */
            oneHourChange.setTextColor(Color.parseColor(when {
                coin.percent_change_1h.contains("-") -> "#ff0000"
                else -> "#32CD32"
            }))

            twentyFourHourChange.setTextColor(Color.parseColor(when {
                coin.percent_change_24h.contains("-") -> "#ff0000"
                else -> "#32CD32"
            }))

            sevenDayChange.setTextColor(Color.parseColor(when {
                coin.percent_change_7d.contains("-") -> "#ff0000"
                else -> "#32CD32"
            }))
        }
    }

    class CryptoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        /**
         * ViewHolder items (textviews, imageviews) from the crypto_layout.xml
         */
        var coinName = view.coinName
        var coinSymbol = view.coinSymbol
        var coinPrice = view.priceUsdText
        val oneHourChange = view.percentChange1hText
        var twentyFourHourChange = view.percentChange24hText
        var sevenDayChange = view.percentChange7dayText
        var coinIcon = view.coinIcon
    }

    fun updateData(cryptoCoins: List<CryptoModel>) {
        this.cryptoCoins = cryptoCoins
        this.notifyDataSetChanged()
    }
}
