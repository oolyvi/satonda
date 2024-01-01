package com.rodoyf.satonda.feature_app.presentation.privacy_policy

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
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
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
fun PrivacyPolicyContent(
    navController: NavController,
) {

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            PrivacyPolicyTopBar(
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
                        text = stringResource(R.string.privacy_policy_for_satonda_application),
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 25.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(CenterHorizontally)
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
                            text = stringResource(R.string._1_introduction),
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
                                    append(stringResource(R.string.this_privacy_policy_outlines_the_practices_of))
                                }

                                withStyle(
                                    style = SpanStyle(
                                        color = MaterialTheme.colorScheme.secondaryContainer
                                    )
                                ) {
                                    append(stringResource(R.string.rodoyf))
                                }

                                withStyle(
                                    style = SpanStyle(
                                        color = MaterialTheme.colorScheme.onSurface,
                                    )
                                ) {
                                    append(
                                        stringResource(R.string.we_our_or_us_regarding) +
                                                stringResource(R.string.the_collection_use) +
                                                stringResource(R.string.and_disclosure_of_personal_information_when_you_use_the_satonda_application)
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
                            text = stringResource(R.string._2_information_we_collect),
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            textAlign = TextAlign.Start,
                        )
                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    SelectionContainer() {
                        Text(
                            text = stringResource(R.string.local_storage_the_app_may_store_certain_user_data_locally_on_your_device) +
                                    stringResource(R.string.for_the_purpose_of_enhancing_your_user_experience_this_data_is_not_shared) +
                                    stringResource(R.string.with_us_or_third_parties) +
                                    stringResource(R.string.internet_access_the_app_requires_internet_access_to_access) +
                                    stringResource(R.string.social_media_accounts_within_the_app_and_to_facilitate_feedback_and) +
                                    stringResource(R.string.bug_reporting_via_email) +
                                    stringResource(R.string.admob_integration_we_have_integrated_admob_for_the) +
                                    stringResource(R.string.display_of_banner_and_interstitial_ads_to_support_the_free_version) +
                                    stringResource(R.string.of_the_app_admob_may_collect_statistics_and_device_identifiers_for) +
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
                            text = stringResource(R.string._3_data_collection_and_use),
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            textAlign = TextAlign.Start,
                        )
                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    SelectionContainer() {
                        Text(
                            text = stringResource(R.string.we_do_not_collect_personal_data_from_users_except_for) +
                                    stringResource(R.string.potential_statistics_provided_by_admob_for_ad_related_purposes) +
                                    stringResource(R.string.any_data_collected_by_admob_is_subject_to_their_privacy_policy),
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.Normal,
                            fontSize = 15.sp,
                            textAlign = TextAlign.Start,
                        )
                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    SelectionContainer() {
                        Text(
                            text = stringResource(R.string._4_feedback_and_bug_reporting),
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            textAlign = TextAlign.Start,
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    SelectionContainer() {
                        Text(
                            text = stringResource(R.string.the_app_provides_the_option_for_users_to_provide_feedback) +
                                    stringResource(R.string.and_report_bugs_via_email_any_information_shared_through) +
                                    stringResource(R.string.this_feature_is_treated_with_confidentiality_and_used_solely) +
                                    stringResource(R.string.for_the_purpose_of_improving_the_app),
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.Normal,
                            fontSize = 15.sp,
                            textAlign = TextAlign.Start,
                        )
                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    SelectionContainer() {
                        Text(
                            text = stringResource(R.string._5_third_party_services),
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            textAlign = TextAlign.Start,
                        )
                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    SelectionContainer() {
                        Text(
                            text = stringResource(R.string.the_app_may_include_links_or_integrations_to_third_party) +
                                    stringResource(R.string.services_such_as_social_media_platforms_please_review) +
                                    stringResource(R.string.the_privacy_policies_of_these_third_party_services_as_your) +
                                    stringResource(R.string.interactions_with_them_are_subject_to_their_respective_terms_and_policies),
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.Normal,
                            fontSize = 15.sp,
                            textAlign = TextAlign.Start,
                        )
                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    SelectionContainer() {
                        Text(
                            text = stringResource(R.string._6_data_security),
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            textAlign = TextAlign.Start,
                        )
                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    SelectionContainer() {
                        Text(
                            text = stringResource(R.string.we_take_reasonable_measures_to_secure_any_data_collected) +
                                    stringResource(R.string.including_admob_statistics_to_protect_it_from_unauthorized) +
                                    stringResource(R.string.access_or_disclosure),
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.Normal,
                            fontSize = 15.sp,
                            textAlign = TextAlign.Start,
                        )
                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    SelectionContainer() {
                        Text(
                            text = stringResource(R.string._7_children_s_privacy),
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            textAlign = TextAlign.Start,
                        )
                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    SelectionContainer() {
                        Text(
                            text = stringResource(R.string.we_do_not_knowingly_collect_personal_information_from) +
                                    stringResource(R.string.children_under_the_age_of_13_without_obtaining_verifiable) +
                                    stringResource(R.string.parental_consent_if_you_believe_that_we_have_inadvertently) +
                                    stringResource(R.string.collected_personal_information_from_a_child_under_13_without) +
                                    stringResource(R.string.proper_consent_please_contact_us_immediately_and_we_will) +
                                    stringResource(R.string.take_steps_to_remove_that_information_from_just_from_ad_services) +
                                    stringResource(R.string.because_we_do_not_collect_any_personal_data_from_you),
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.Normal,
                            fontSize = 15.sp,
                            textAlign = TextAlign.Start,
                        )
                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    SelectionContainer() {
                        Text(
                            text = stringResource(R.string._8_information_collection_from_children),
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            textAlign = TextAlign.Start,
                        )
                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    SelectionContainer() {
                        Text(
                            text = stringResource(R.string.the_app_may_collect_non_personal_information_from) +
                                    stringResource(R.string.children_such_as_device_identifiers_and_usage_statistics) +
                                    stringResource(R.string.this_information_is_used_solely_for_the_purpose_of_improving) +
                                    stringResource(R.string.the_app_and_is_not_used_to_identify_individual_users),
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.Normal,
                            fontSize = 15.sp,
                            textAlign = TextAlign.Start,
                        )
                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    SelectionContainer() {
                        Text(
                            text = stringResource(R.string._9_parental_rights),
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            textAlign = TextAlign.Start,
                        )
                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    SelectionContainer() {
                        Text(
                            text = stringResource(R.string.parents_have_the_right_to_review_update_or) +
                                    stringResource(R.string.delete_any_personal_information_collected_from) +
                                    stringResource(R.string.their_children_parents_can_also_revoke_their_consent) +
                                    stringResource(R.string.at_any_time_by_contacting_us),
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.Normal,
                            fontSize = 15.sp,
                            textAlign = TextAlign.Start,
                        )
                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    SelectionContainer() {
                        Text(
                            text = stringResource(R.string._10_changes_to_this_privacy_policy),
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            textAlign = TextAlign.Start,
                        )
                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    SelectionContainer() {
                        Text(
                            text = stringResource(R.string.we_may_update_this_privacy_policy_to_reflect_changes) +
                                    stringResource(R.string.in_our_data_practices_we_will_notify_you_of_any_material) +
                                    stringResource(R.string.changes_through_the_app_or_via_other_means_this_policy_is_effective) +
                                    stringResource(R.string.as_of_privacy_policy_data),
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.Normal,
                            fontSize = 15.sp,
                            textAlign = TextAlign.Start,
                        )
                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    SelectionContainer() {
                        Text(
                            text = stringResource(R.string._11_contact_us),
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
                                        stringResource(R.string.if_you_have_any_questions_suggestions_or_concerns_about) +
                                                stringResource(R.string.this_privacy_policy_do_not_hesitate_to_contact_us_at)
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