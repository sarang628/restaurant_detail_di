package com.sarang.torang.di.restaurant_detail_di

import com.sarang.library.data.MenuData
import com.sarang.library.data.RestaurantImage
import com.sarang.library.usecase.GetMenuUseCase
import com.sarang.library.usecase.GetRestaurantGalleryUseCase
import com.sarang.torang.api.ApiRestaurant
import com.sarang.torang.di.restaurant_overview_di.toMenus
import com.sarang.torang.di.restaurant_overview_di.toRestaurantImages
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class RestaurantServiceModule {

    @Provides
    fun providesGetRestaurantGalleryUseCase(
        apiRestaurant: ApiRestaurant,
    ): GetRestaurantGalleryUseCase {
        return object : GetRestaurantGalleryUseCase {
            override suspend fun invoke(restaurantId: Int): List<RestaurantImage> {
                return apiRestaurant.getRestaurantDetail(restaurantId).toRestaurantImages()
            }
        }
    }

    @Provides
    fun providesGetMenuUseCase(apiRestaurant: ApiRestaurant): GetMenuUseCase {
        return object : GetMenuUseCase {
            override suspend fun invoke(restaurantId: Int): List<MenuData> {
                return apiRestaurant.getRestaurantDetail(restaurantId).toMenus()
            }
        }
    }
}