package com.rodoyf.satonda.feature_app.presentation.terms

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.rodoyf.satonda.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TermsContent(
    navController: NavController,
) {

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TermsTopBar(
                navController = navController
            )
        }
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 45.dp)
                .background(MaterialTheme.colorScheme.surface),
            contentPadding = PaddingValues(
                bottom = 70.dp,
                top = 20.dp
            )
        ) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(start = 15.dp, end = 15.dp)
                ) {
                    Text(
                        text = stringResource(R.string.terms_conditions_for_satonda_application),
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 25.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )


                    Spacer(modifier = Modifier.height(20.dp))

                    Divider(thickness = 1.dp, color = MaterialTheme.colorScheme.onSurface)

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = stringResource(R.string.effective_date_privacy_policy),
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Light,
                        fontSize = 13.sp,
                        textAlign = TextAlign.Start,
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    SelectionContainer() {
                        Text(
                            text = stringResource(R.string._1_acceptance_of_terms),
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            textAlign = TextAlign.Start,
                        )
                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    SelectionContainer() {
                        Text(
                            buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        color = MaterialTheme.colorScheme.onSurface,
                                    )
                                ) {
                                    append(stringResource(R.string.by_using_the))
                                }

                                withStyle(
                                    style = SpanStyle(
                                        color = MaterialTheme.colorScheme.secondaryContainer
                                    )
                                ) {
                                    append(stringResource(R.string.satonda))
                                }

                                withStyle(
                                    style = SpanStyle(
                                        color = MaterialTheme.colorScheme.onSurface,
                                    )
                                ) {
                                    append(
                                        stringResource(R.string.app_you_agree_to_be_bound_by_these_terms_conditions) +
                                                stringResource(R.string.if_you_do_not_agree_with_any_part_of_these_terms_please) +
                                                stringResource(R.string.do_not_use_the_app)
                                    )
                                }
                            },
                            fontWeight = FontWeight.Normal,
                            fontSize = 15.sp,
                            textAlign = TextAlign.Start,
                        )
                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    SelectionContainer() {
                        Text(
                            text = stringResource(R.string._2_use_of_the_app),
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            textAlign = TextAlign.Start,
                        )
                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    SelectionContainer() {
                        Text(
                            text = stringResource(R.string.the_app_is_intended_for_personal_non_commercial_use) +
                                    stringResource(R.string.you_agree_not_to_use_the_app_for_any_unlawful_or_prohibited_purpose),
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.Normal,
                            fontSize = 15.sp,
                            textAlign = TextAlign.Start,
                        )
                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    SelectionContainer() {
                        Text(
                            text = stringResource(R.string._3_data_usage),
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            textAlign = TextAlign.Start,
                        )
                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    SelectionContainer() {
                        Text(
                            text = stringResource(R.string.the_app_uses_local_storage_to_save_user_data_this_data_is_stored) +
                                    stringResource(R.string.locally_on_your_device_and_is_not_shared_with_us_or_third_parties) +
                                    stringResource(R.string.the_app_requires_internet_access_to_access_social_media_accounts_within) +
                                    stringResource(R.string.the_app_and_to_facilitate_feedback_and_bug_reporting_via_email) +
                                    stringResource(R.string.the_app_features_admob_integration_which_displays_banner_and_interstitial) +
                                    stringResource(R.string.ads_for_users_admob_may_collect_statistics_and_device_identifiers_for) +
                                    stringResource(R.string.personalized_ad_targeting_please_refer_to_admob_s_privacy_policy_for_details),
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.Normal,
                            fontSize = 15.sp,
                            textAlign = TextAlign.Start,
                        )
                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    SelectionContainer() {
                        Text(
                            text = stringResource(R.string._4_user_data_collection),
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            textAlign = TextAlign.Start,
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    SelectionContainer() {
                        Text(
                            text = stringResource(R.string.we_do_not_collect_personal_data_from_users_except_for_potential) +
                                    stringResource(R.string.statistics_provided_by_admob_for_ad_related_purposes_any_data) +
                                    stringResource(R.string.collected_by_admob_is_subject_to_their_privacy_policy),
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.Normal,
                            fontSize = 15.sp,
                            textAlign = TextAlign.Start,
                        )
                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    SelectionContainer() {
                        Text(
                            text = stringResource(R.string._5_feedback_and_bug_reporting),
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            textAlign = TextAlign.Start,
                        )
                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    SelectionContainer() {
                        Text(
                            text = stringResource(R.string.the_app_provides_the_option_for_users_to_provide_feedback) +
                                    stringResource(R.string.and_report_bugs_via_email_any_information_shared_through_this) +
                                    stringResource(R.string.feature_is_treated_with_confidentiality_and_used_solely_for_the) +
                                    stringResource(R.string.purpose_of_improving_the_app),
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.Normal,
                            fontSize = 15.sp,
                            textAlign = TextAlign.Start,
                        )
                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    SelectionContainer() {
                        Text(
                            text = stringResource(R.string._6_intellectual_property),
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            textAlign = TextAlign.Start,
                        )
                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    SelectionContainer() {
                        Text(
                            text = stringResource(R.string.the_app_and_its_content_are_protected_by_intellectual_property_laws) +
                                    stringResource(R.string.you_may_not_reproduce_distribute_or_create_derivative_works_based) +
                                    stringResource(R.string.on_the_app_without_our_express_permission),
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.Normal,
                            fontSize = 15.sp,
                            textAlign = TextAlign.Start,
                        )
                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    SelectionContainer() {
                        Text(
                            text = stringResource(R.string._7_disclaimer_of_warranties),
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            textAlign = TextAlign.Start,
                        )
                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    SelectionContainer() {
                        Text(
                            text = stringResource(R.string.the_app_is_provided_and_we_make_no_warranties_or) +
                                    stringResource(R.string.representations_regarding_its_functionality_or_availability) +
                                    stringResource(R.string.we_disclaim_all_warranties_whether_express_or_implied),
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.Normal,
                            fontSize = 15.sp,
                            textAlign = TextAlign.Start,
                        )
                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    SelectionContainer() {
                        Text(
                            text = stringResource(R.string._8_limitation_of_liability),
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            textAlign = TextAlign.Start,
                        )
                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    SelectionContainer() {
                        Text(
                            text = stringResource(R.string.to_the_fullest_extent_permitted_by_law_we_shall_not_be) +
                                    stringResource(R.string.liable_for_any_direct_indirect_incidental_special_or) +
                                    stringResource(R.string.consequential_damages_arising_from_or_related_to_the_use_of_the_app),
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.Normal,
                            fontSize = 15.sp,
                            textAlign = TextAlign.Start,
                        )
                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    SelectionContainer() {
                        Text(
                            text = stringResource(R.string._9_changes_to_terms_and_conditions),
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            textAlign = TextAlign.Start,
                        )
                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    SelectionContainer() {
                        Text(
                            text = stringResource(R.string.we_may_update_these_terms_conditions_at_any_time_any) +
                                    stringResource(R.string.changes_will_be_effective_upon_posting_on_the_app_your_continued) +
                                    stringResource(R.string.use_of_the_app_after_such_changes_constitutes_your_acceptance_of) +
                                    stringResource(R.string.the_revised_terms_these_terms_conditions_are_effective_as_of_01_10_2023),
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.Normal,
                            fontSize = 15.sp,
                            textAlign = TextAlign.Start,
                        )
                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    SelectionContainer() {
                        Text(
                            text = stringResource(R.string._10_contact_us),
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            textAlign = TextAlign.Start,
                        )
                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    SelectionContainer() {
                        Text(
                            buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        color = MaterialTheme.colorScheme.onSurface,
                                    )
                                ) {
                                    append(
                                        stringResource(R.string.if_you_have_any_questions_suggestions_or_concerns_about_these_terms_conditions) +
                                                stringResource(R.string.please_contact_us_at)
                                    )
                                }

                                withStyle(
                                    style = SpanStyle(
                                        color = MaterialTheme.colorScheme.secondaryContainer
                                    )
                                ) {
                                    append(
                                        stringResource(R.string.rodoyf_corp_gmail_com)
                                    )
                                }
                            },
                            fontWeight = FontWeight.Normal,
                            fontSize = 15.sp,
                            textAlign = TextAlign.Start,
                        )
                    }
                }
            }
        }
    }
}